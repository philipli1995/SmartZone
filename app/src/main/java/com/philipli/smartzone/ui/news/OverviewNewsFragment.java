package com.philipli.smartzone.ui.news;

import android.content.Intent;
import android.opengl.GLDebugHelper;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookPagerAdapter;
import com.philipli.smartzone.adapter.NewsPagerAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.ui.MainActivity;
import com.philipli.smartzone.util.DebugUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philipli on 2017/10/17.
 */

public class OverviewNewsFragment extends RxBaseFragment {


    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.add_channel)
    ImageView mAddChannel;

    NewsPagerAdapter adapter;



    public static OverviewNewsFragment getInstance() {
        return new OverviewNewsFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_news_type_overview;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
        initViewPager();
    }

    private void initViewPager() {
        adapter = new NewsPagerAdapter(getChildFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        mTabLayout.setupWithViewPager(mViewPager);
        setTabLayoutMode();
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ((GeneralNewsFragment)(adapter.getItem(tab.getPosition()))).scrollUp();
            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 1) {
            //改变adapter的方案，已弃用
//            adapter.updateAdapter();
//            adapter.test();
//            adapter.notifyDataSetChanged();
//            adapter = new NewsPagerAdapter(getChildFragmentManager(), getApplicationContext());
//            mViewPager.setOffscreenPageLimit(3);
//            mViewPager.setAdapter(adapter);
//            mViewPager.setCurrentItem(0);
//            setTabLayoutMode();
            ((MainActivity)getSupportActivity()).reloadFragment();
        }
    }

    @OnClick(R.id.add_channel)
    public void onClick() {
        Intent intent = new Intent(this.getContext(), ChannelSetupActivity.class);
        startActivityForResult(intent, 0, null);
    }

    /*
        根据频道个数计算TabLayout模式是 FIXED 或 SCROLLABLE
    */
    private void setTabLayoutMode() {


        int tabWidth = calculateTabWidth(mTabLayout);
        // 别忘了右边还有添加按钮
        int screenWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        mAddChannel.measure(0,0);
        int imageWidth = mAddChannel.getMeasuredWidth();
        if (tabWidth <= screenWidth - imageWidth) {
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        } else {
            mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
    }

    private static int calculateTabWidth(TabLayout tabLayout) {
        int tabWidth = 0;
        for (int i = 0; i < tabLayout.getChildCount(); i++) {
            final View view = tabLayout.getChildAt(i);
            view.measure(0, 0); // 通知父view测量，以便于能够保证获取到宽高
            tabWidth += view.getMeasuredWidth();
        }
        return tabWidth;
    }

}
