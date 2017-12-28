package com.philipli.smartzone.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.widget.CustomEmptyView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philipli on 2017/12/18.
 */

public class AboutAuthorActivity extends RxBaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about_author_layout;
    }

    @Override
    public void initToolBar() {

        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_chevron_left_white_24dp);

        }
        mToolBar.setNavigationOnClickListener(aLong -> onBackPressed());
        mToolBar.setTitleTextAppearance(this, R.style.ToolBar_Title);
        mToolBar.setTitle("关于");

    }



    @Override
    public void initViews(Bundle savedInstanceState) {

    }


    @Override
    public void loadData() {


    }

    @OnClick({R.id.tv_blog, R.id.tv_github})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.tv_blog:
                WebViewActivity.start(this, "个人博客", "http://philipli-blog.com/");
                break;
            case R.id.tv_github:
                WebViewActivity.start(this, "Github", "https://github.com/PhilipLi1995/");
                break;
            default:
                break;
        }

    }


    public static void start(Context context) {

        Intent intent = new Intent(context, AboutAuthorActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }

}

