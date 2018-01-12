package com.philipli.smartzone.ui.news;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookAdapter;
import com.philipli.smartzone.adapter.NewsAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.bean.NewsDetail;
import com.philipli.smartzone.bean.NewsSummary;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.widget.CustomEmptyView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by philipli on 2017/12/6.
 */

public class GeneralNewsFragment extends RxBaseFragment {

    private static final int LOAD_COUNT = 20;

    private static final String TITLE = "title";
    private static final String TYPE = "type";
    private static final String ID = "id";

    private int mLoadStartIndex = 0;

    private String mTitle;
    private String mType;
    private String mId;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.recycle)
    RecyclerView mRecycleView;

    NewsAdapter mNewsAdapter;
    LinearLayoutManager mLayoutManager;
    private List<NewsSummary> mList = new ArrayList<>();

    public static GeneralNewsFragment getInstance(String title, String type, String id) {
        GeneralNewsFragment generalNewsFragment = new GeneralNewsFragment();
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        args.putString(TYPE, type);
        args.putString(ID, id);
        generalNewsFragment.setArguments(args);
        return generalNewsFragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_child_list_fragment;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
        if (getArguments() != null) {
            mTitle = getArguments().getString(TITLE);
            mType = getArguments().getString(TYPE);
            mId = getArguments().getString(ID);
        }

        load();

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

                    if (mNewsAdapter == null) {
                        return;
                    }
                    if (mLayoutManager.getItemCount() == 0) {
                        mNewsAdapter.updateStatus(BookAdapter.LOAD_END);
                        return;

                    }
                    if (lastVisibleItem + 1 == mLayoutManager.getItemCount()
                            && mNewsAdapter.getStatus() != BookAdapter.LOAD_LOADING) {
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

        mNewsAdapter = new NewsAdapter(this.getContext(), mList);
        mLayoutManager = new LinearLayoutManager(mRecycleView.getContext());

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mNewsAdapter);

    }

    private void load() {

        RetrofitConfig.getNeteaseAPI().getNewsList(mType, mId, mLoadStartIndex)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, List<NewsSummary>>>() {
                    @Override
                    public void accept(Map<String, List<NewsSummary>> stringNewsSummaryMap) throws Exception {
                        mList.addAll(stringNewsSummaryMap.entrySet().iterator().next().getValue());
                        finishTask();
                    }
                }, throwable -> initEmptyView());

    }

    private void clearAll() {
        mList.clear();
    }

    protected void finishTask() {
        mNewsAdapter.onNetworkWorked();
        mNewsAdapter.updateStatus(NewsAdapter.LOAD_IDLE);
        mNewsAdapter.updateList(mList);
        mSwipeRefreshLayout.setRefreshing(false);
        mNewsAdapter.notifyDataSetChanged();
    }

    public void scrollUp() {
        mRecycleView.smoothScrollToPosition(0);
    }


    private void initEmptyView() {

        mNewsAdapter.onNetworkFailed();
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
