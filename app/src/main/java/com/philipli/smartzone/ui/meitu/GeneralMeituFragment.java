package com.philipli.smartzone.ui.meitu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookAdapter;
import com.philipli.smartzone.adapter.GeneralAdapter;
import com.philipli.smartzone.adapter.MeituAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.bean.MeituBean;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.widget.CustomEmptyView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * Created by philipli on 2017/12/6.
 */

public class GeneralMeituFragment extends RxBaseFragment {

    private static final int LOAD_COUNT = 20;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;
    private GridLayoutManager mLayoutManager;
    private MeituAdapter mMeituAdapter;

    private int mLoadStartIndex = 1;

    private final String TYPE = "福利";


    private List<String> mList = new ArrayList<>();

    public static GeneralMeituFragment getInstance() {
        GeneralMeituFragment generalMeituFragment = new GeneralMeituFragment();
        return generalMeituFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_child_list_fragment;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
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

                    if (mMeituAdapter == null) {
                        return;
                    }
                    if (mLayoutManager.getItemCount() == 0) {
                        mMeituAdapter.updateStatus(BookAdapter.LOAD_END);
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()
                            && mMeituAdapter.getStatus() != BookAdapter.LOAD_LOADING) {
                        mLoadStartIndex += 1;
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
        mMeituAdapter = new MeituAdapter(this.getContext(), mList);
        mLayoutManager = new GridLayoutManager(mRecycleView.getContext(), 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (mMeituAdapter.getItemViewType(position)){
                    case GeneralAdapter.HEADER : {
                        return 2;
                    }
                    case GeneralAdapter.CONTENT: {
                        return 1;
                    }
                    case GeneralAdapter.FOOTER: {
                        return 2;
                    }
                    default:
                        return 1;

                }
            }
        });
        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mMeituAdapter);

    }

    private void load() {
        mSwipeRefreshLayout.setRefreshing(true);
        mMeituAdapter.updateStatus(BookAdapter.LOAD_LOADING);

        RetrofitConfig.getMeituAPI().getMeitu(TYPE, LOAD_COUNT, mLoadStartIndex)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MeituBean>() {
                    @Override
                    public void accept(MeituBean meituBean) throws Exception {
                        for (MeituBean.ResultBean resultBean : meituBean.getResults()) {
                            mList.add(resultBean.getUrl());
                        }
                        finishTask();
                    }
                }, throwable -> initEmptyView());

    }

    private void clearAll() {
        mList.clear();
    }

    protected void finishTask() {
        mMeituAdapter.onNetworkWorked();
        mMeituAdapter.updateStatus(BookAdapter.LOAD_IDLE);
        mMeituAdapter.updateList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
//        mBookAdapter = new BookAdapter(this.getContext(), mList);
//        mRecycleView.setAdapter(mBookAdapter);
        mMeituAdapter.notifyDataSetChanged();
    }

    public void scrollUp() {
        mRecycleView.smoothScrollToPosition(0);
    }



    private void initEmptyView() {

        mMeituAdapter.onNetworkFailed();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
