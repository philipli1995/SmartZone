package com.philipli.smartzone.ui.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.ChannelOptionAdapter;
import com.philipli.smartzone.app.SmartZoneApp;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.ui.WebViewActivity;
import com.philipli.smartzone.util.CommonUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philipli on 2017/12/18.
 */

public class ChannelSetupActivity extends RxBaseActivity {


    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.setup_my_channel)
    RecyclerView mMyChannelrv;
    @BindView(R.id.setup_all_channel)
    RecyclerView mAllChaneelrv;

    GridLayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setup_channel;
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
        mToolBar.setTitle("频道设置");


    }

    @Override
    public void initViews(Bundle savedInstanceState) {

        List<Integer> myChannel = CommonUtil.getMyChannels();
        List<Integer> allChannel = CommonUtil.getAllChannels(myChannel);
        ChannelOptionAdapter myChannelAdapter = new ChannelOptionAdapter(this, myChannel);
        ChannelOptionAdapter allChannelAdapter = new ChannelOptionAdapter(this, allChannel);
        myChannelAdapter.setOnItemClickListener(new ChannelOptionAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                if (myChannelAdapter.getItemCount() == 1) {
                    Toast.makeText(SmartZoneApp.getInstance(), "请至少保留一个频道~", Toast.LENGTH_SHORT).show();
                    return;
                }
                int item = myChannelAdapter.removeItem(position);
                allChannelAdapter.addItem(item);
                myChannelAdapter.notifyDataSetChanged();
                allChannelAdapter.notifyDataSetChanged();
            }
        });
        allChannelAdapter.setOnItemClickListener(new ChannelOptionAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position, View view) {
                if(myChannelAdapter.getItemCount() == 8) {
                    Toast.makeText(SmartZoneApp.getInstance(), "设置太多频道会让应用崩溃哦~", Toast.LENGTH_SHORT).show();
                    return;
                }
                int item = allChannelAdapter.removeItem(position);
                myChannelAdapter.addItem(item);
                myChannelAdapter.notifyDataSetChanged();
                allChannelAdapter.notifyDataSetChanged();
            }
        });
        mMyChannelrv.setLayoutManager(new GridLayoutManager(mMyChannelrv.getContext(), 4));
        mAllChaneelrv.setLayoutManager(new GridLayoutManager(mMyChannelrv.getContext(), 4));
        mMyChannelrv.setAdapter(myChannelAdapter);
        mAllChaneelrv.setAdapter(allChannelAdapter);




    }

    @OnClick(R.id.channel_save_btn)
    public void saveChannel() {
        CommonUtil.setMyChannels(((ChannelOptionAdapter)mMyChannelrv.getAdapter()).getChannels());
        this.setResult(1);
        Toast.makeText(SmartZoneApp.getInstance(), "保存成功~ ^_^", Toast.LENGTH_SHORT).show();

    }




    @Override
    public void loadData() {


    }

}

