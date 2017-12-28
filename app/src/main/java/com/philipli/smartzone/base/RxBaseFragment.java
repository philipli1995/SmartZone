package com.philipli.smartzone.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by philipli on 2017/10/17.
 */

public abstract class RxBaseFragment extends com.trello.rxlifecycle2.components.support.RxFragment {

    private View parentView;
    private FragmentActivity activity;

    protected boolean prepared;

    protected boolean visible;

    private Unbinder bind;


    @LayoutRes
    public abstract int getLayoutId();

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = inflater.inflate(getLayoutId(), container, false);
        activity = getSupportActivity();
        return parentView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }

    /**
     * initialize view
     * @param savedInstanceState
     */
    public abstract void finishCreateView(Bundle savedInstanceState);

    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }
    public ActionBar getActionBar() {
        return super.getActivity().getActionBar();
    }

    public Context getApplicationContext() {
        if (this.activity == null) {
            if (this.getActivity() == null) {
                return null;
            }
            return getActivity().getApplicationContext();
        }
        return this.activity.getApplicationContext();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            visible = true;
            onVisible();
        }
        else {
            visible = false;
            onInvisible();
        }
    }

    protected void finishTask() {

    }

    /**
     * 初始化recyclerView
     */
    protected void initRecyclerView() {
    }

    /**
     * 初始化refreshLayout
     */
    protected void initRefreshLayout() {
    }

    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }

    protected void onVisible() {

    }

    protected void onInvisible() {


    }



}
