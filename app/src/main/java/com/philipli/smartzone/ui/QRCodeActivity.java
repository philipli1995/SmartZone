package com.philipli.smartzone.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.util.QRcodeUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philipli on 2017/12/18.
 */

public class QRCodeActivity extends RxBaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.tv_version_name)
    TextView mVersionName;
    @BindView(R.id.qr_icon)
    ImageView mQrView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_scan_download;
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
        mToolBar.setTitle("扫码下载");

    }



    @Override
    public void initViews(Bundle savedInstanceState) {

        String text = "当前版本 " + getResources().getString(R.string.current_version);

        mVersionName.setText(text);

        QRcodeUtil.showThreadImage(this, getResources().getString(R.string.download_url), mQrView, R.drawable.ic_logo);

    }


    @Override
    public void loadData() {


    }



    public static void start(Context context) {

        Intent intent = new Intent(context, QRCodeActivity.class);
        ActivityCompat.startActivity(context, intent, null);
    }

}

