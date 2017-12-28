package com.philipli.smartzone.base;

import android.os.Bundle;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by philip on 2017/10/10.
 *
 *
 */

public abstract class RxBaseActivity extends RxAppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局内容
        setContentView(getLayoutId());
        //初始化黄油刀控件绑定框架
        bind = ButterKnife.bind(this);
        //初始化控件
        initViews(savedInstanceState);
        //初始化ToolBar
        initToolBar();

    }

    /**
    *  Created by philip on 2017/10/10 21:52.
    */
    public abstract int getLayoutId();

    /**
    *  Created by philip on 2017/10/10 22:22.
    */
    public abstract void initToolBar();

    /**
    *  Created by philip on 2017/10/10 22:23.
     *
     *  initialize Views
     *
     *  @param savedInstanceState
    */
    public abstract void initViews(Bundle savedInstanceState);


    /**
    *  Created by philip on 2017/10/10 22:24.
    */
    public void loadData() {

    }

    public void showProgressBar() {

    }
    public void hideProgressBar() {

    }

    public void initRecyclerView() {

    }
    public void initRefreshLayout() {

    }
    public void finishTask() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
