package com.philipli.smartzone.ui.books;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.widget.CustomEmptyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by philipli on 2017/12/6.
 */

public class GeneralBookFragment extends RxBaseFragment {

    private static final int LOAD_COUNT = 21;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    @BindView(R.id.empty_view)
    CustomEmptyView mEmptyView;
    private GridLayoutManager mLayoutManager;
    private BookAdapter mBookAdapter;

    private static final String TYPE = "param1";

    private int mLoadStartIndex = 0;
    private String mType;


    private List<BooksBean.BookBean> mList = new ArrayList<>();

    public static GeneralBookFragment getInstance(String type) {
        GeneralBookFragment generalBookFragment = new GeneralBookFragment();
        Bundle args = new Bundle();
        args.putString(TYPE, type);
        generalBookFragment.setArguments(args);
        return generalBookFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_child_list_fragment;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mType = getArguments().getString(TYPE);
        }
        initRefreshLayout();
        initRecyclerView();
        scrollRecycleView();

    }

    private void scrollRecycleView() {
        mRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            int lastVisibleItem;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                    if (mBookAdapter == null) {
                        return;
                    }
                    if (mLayoutManager.getItemCount() == 0) {
                        mBookAdapter.updateStatus(BookAdapter.LOAD_END);
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()
                            && mBookAdapter.getStatus() != BookAdapter.LOAD_LOADING) {
                        mLoadStartIndex += LOAD_COUNT;
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                load();
                            }
                        });
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

                /**StaggeredGridLayoutManager*/
//                int[] into = new int[(mLayoutManager).getSpanCount()];
//                lastVisibleItem = findMax(mLayoutManager.findLastVisibleItemPositions(into));
            }
        });
    }

    protected void initRefreshLayout() {

        mSwipeRefreshLayout.post(() -> {
            mSwipeRefreshLayout.setRefreshing(true);
            load();
        });

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            clearAll();
            mLoadStartIndex = 0;
            load();
        });
    }

    @Override
    protected void initRecyclerView() {
        mBookAdapter = new BookAdapter(this.getContext(), mList);
        mLayoutManager = new GridLayoutManager(mRecycleView.getContext(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mBookAdapter.getItemViewType(position)){
                    case BookAdapter.BOOK_HEADER : {
                        return 3;
                    }
                    case BookAdapter.BOOK_CONTENT: {
                        return 1;
                    }
                    case BookAdapter.BOOK_FOOTER: {
                        return 3;
                    }
                    default:
                        return 1;

                }
            }
        });
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mBookAdapter);

    }

    private void load() {
        mSwipeRefreshLayout.setRefreshing(true);
        mBookAdapter.updateStatus(BookAdapter.LOAD_LOADING);
        RetrofitConfig.getDoubanAPI().getBook(mType, mLoadStartIndex, LOAD_COUNT)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BooksBean>() {
                    @Override
                    public void accept(BooksBean booksBean) throws Exception {
                        mList.addAll(booksBean.getBooks());
                        finishTask();
                    }
                }, throwable -> initEmptyView());
    }

    private void clearAll() {
        mList.clear();
    }

    protected void finishTask() {
        hideView();
        mBookAdapter.updateStatus(BookAdapter.LOAD_IDLE);
        mBookAdapter.updateList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
//        mBookAdapter = new BookAdapter(this.getContext(), mList);
//        mRecycleView.setAdapter(mBookAdapter);
        mBookAdapter.notifyDataSetChanged();
    }

    private void hideView() {
        mEmptyView.setVisibility(View.GONE);
        mRecycleView.setVisibility(View.VISIBLE);
    }

    private void initEmptyView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mRecycleView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
        mEmptyView.setImage(R.drawable.ic_logo);
        mEmptyView.setText("This is an empty page!");
    }

}
