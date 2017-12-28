package com.philipli.smartzone.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.bean.NewsDetail;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.util.GlideUtil;
import com.philipli.smartzone.widget.CustomEmptyView;

import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by philipli on 2017/12/18.
 */

public class WebViewActivity extends RxBaseActivity {


    private static final String INTENT_NAME = "title";
    private static final String INTENT_URL = "img";

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.empty_text)
    TextView mEmptyText;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.empty_view)
    CustomEmptyView mEmptyView;





    private String title;
    private String url;


    @Override
    public int getLayoutId() {
        return R.layout.activity_webview_layout;
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

//        mToolBar.setOverflowIcon(getResources().getDrawable(R.drawable.actionbar_more));
        mToolBar.setNavigationOnClickListener(aLong -> onBackPressed());
        mToolBar.setTitleTextAppearance(this, R.style.ToolBar_Title);
        mToolBar.setTitle(title);

    }



    @Override
    public void initViews(Bundle savedInstanceState) {

        title = (String) getIntent().getSerializableExtra(INTENT_NAME);
        url = (String) getIntent().getSerializableExtra(INTENT_URL);

        initWebView();

    }

    private void initWebView() {
//        initEmptyView();
        WebSettings ws = mWebView.getSettings();
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);
        // 保存表单数据
        ws.setSaveFormData(true);
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // 启动应用缓存
        ws.setAppCacheEnabled(true);
        // 设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);
        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        // 缩放比例 1
        mWebView.setInitialScale(1);
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        // WebView是否支持多个窗口。
        ws.setSupportMultipleWindows(true);

        // webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        /** 设置字体默认缩放大小(改变网页字体大小,setTextSize  api14被弃用)*/
        ws.setTextZoom(100);
        mWebView.loadUrl(url);


//        hideEmptyView();

//        mWebChromeClient = new MyWebChromeClient(this);
//        webView.setWebChromeClient(mWebChromeClient);
//        // 与js交互
//        webView.addJavascriptInterface(new ImageClickInterface(this), "injectedObject");
//        webView.setWebViewClient(new MyWebViewClient(this));
    }


    @Override
    public void loadData() {


    }


    private void hideEmptyView() {
        mWebView.setVisibility(View.VISIBLE);
        mEmptyView.setVisibility(View.INVISIBLE);
    }

    private void initEmptyView() {

        mWebView.setVisibility(View.INVISIBLE);
        mEmptyView.setVisibility(View.VISIBLE);
        mEmptyView.setText("抱歉，由于网络原因无法观看...(ಥ_ಥ)");
    }

    public static void start(Context context, String title, String url) {

        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(INTENT_NAME, title);
        intent.putExtra(INTENT_URL, url);
        ActivityCompat.startActivity(context, intent, null);
    }

}

