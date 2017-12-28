package com.philipli.smartzone.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.SlidingTabLayout;
import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookPagerAdapter;
import com.philipli.smartzone.adapter.NewsPagerAdapter;
import com.philipli.smartzone.base.RxBaseFragment;

import butterknife.BindView;

/**
 * Created by philipli on 2017/10/17.
 */

public class OverviewNewsFragment extends RxBaseFragment {


    @BindView(R.id.sliding_tabs)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;



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
        NewsPagerAdapter adapter = new NewsPagerAdapter(getChildFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setupWithViewPager(mViewPager);

    }

}
