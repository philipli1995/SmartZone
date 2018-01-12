package com.philipli.smartzone.ui.books;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookPagerAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.ui.books.GeneralBookFragment;

import butterknife.BindView;

/**
 * Created by philipli on 2017/10/17.
 */

public class OverviewBookFragment extends RxBaseFragment {


    @BindView(R.id.sliding_tabs)
    SlidingTabLayout mSlidingTabs;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;



    public static OverviewBookFragment getInstance() {
        return new OverviewBookFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_child_overview;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
        initViewPager();
    }

    private void initViewPager() {
        BookPagerAdapter adapter = new BookPagerAdapter(getChildFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mSlidingTabs.setViewPager(mViewPager);
        mSlidingTabs.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {

            }

            @Override
            public void onTabReselect(int position) {
                ((GeneralBookFragment)adapter.getItem(position)).scrollUp();
            }
        });

    }

}
