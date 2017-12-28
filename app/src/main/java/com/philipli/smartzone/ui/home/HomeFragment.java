package com.philipli.smartzone.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.BookPagerAdapter;
import com.philipli.smartzone.adapter.HomePagerAdapter;
import com.philipli.smartzone.base.RxBaseFragment;
import com.philipli.smartzone.ui.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by philipli on 2017/10/17.
 */

public class HomeFragment extends RxBaseFragment {


    @BindView(R.id.toggle_btn)
    ImageView mToggleBtn;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.book_btn)
    ImageView mBookBtn;
    @BindView(R.id.mm_btn)
    ImageView mMmBtn;
    @BindView(R.id.news_btn)
    ImageView mNewsBnt;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private static final int BOOK_PAGE = 0;
    private static final int NEWS_PAGE = 1;
    private static final int MM_PAGE = 2;

    private int status = 0;


    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_homepage;
    }

    @Override
    public void finishCreateView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        initToolBar();
        initViewPager();


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
        mBookBtn.setSelected(true);


    }

    @OnClick(R.id.toggle_btn)
    void toggleDrawer() {
        Activity activity = getActivity();
        if (activity instanceof MainActivity) {
            ((MainActivity) activity).toggleDrawer();
        }
    }

    @OnClick({R.id.book_btn, R.id.mm_btn, R.id.news_btn})
    void statusBarOnClick(View view) {
        switch (view.getId()) {
            case R.id.book_btn:
                if(status == BOOK_PAGE) return;
                mBookBtn.setSelected(true);
                mMmBtn.setSelected(false);
                mNewsBnt.setSelected(false);
                status = BOOK_PAGE;
                mViewPager.setCurrentItem(0);
                break;
            case R.id.mm_btn:
                if (status == MM_PAGE) return;
                mMmBtn.setSelected(true);
                mBookBtn.setSelected(false);
                mNewsBnt.setSelected(false);
                status = MM_PAGE;
                mViewPager.setCurrentItem(1);
                break;
            case R.id.news_btn:
                if (status == NEWS_PAGE) return;
                mNewsBnt.setSelected(true);
                mBookBtn.setSelected(false);
                mMmBtn.setSelected(false);
                status = NEWS_PAGE;
                mViewPager.setCurrentItem(2);
                break;
        }
    }




    private void initViewPager() {
        HomePagerAdapter adapter = new HomePagerAdapter(getChildFragmentManager(), getApplicationContext());
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(adapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBookBtn.setSelected(true);
                        mMmBtn.setSelected(false);
                        mNewsBnt.setSelected(false);
                        status = BOOK_PAGE;
                        break;
                    case 1:
                        mBookBtn.setSelected(false);
                        mMmBtn.setSelected(true);
                        mNewsBnt.setSelected(false);
                        status = MM_PAGE;
                        break;
                    case 2:
                        mBookBtn.setSelected(false);
                        mMmBtn.setSelected(false);
                        mNewsBnt.setSelected(true);
                        status = NEWS_PAGE;
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
