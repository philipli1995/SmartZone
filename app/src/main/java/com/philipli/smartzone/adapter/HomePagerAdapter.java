package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.philipli.smartzone.R;
import com.philipli.smartzone.test.SimpleFragment;
import com.philipli.smartzone.ui.books.GeneralBookFragment;
import com.philipli.smartzone.ui.books.OverviewBookFragment;
import com.philipli.smartzone.ui.meitu.GeneralMeituFragment;
import com.philipli.smartzone.ui.news.OverviewNewsFragment;


/**
 * Created by philipli on 2017/10/18.
 */

public class HomePagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public HomePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.home_tabs);
        fragments = new Fragment[TITLES.length];

    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {

            switch (position) {
                case 0:
                    fragments[0] = OverviewBookFragment.getInstance();
                    break;
                case 1:
                    fragments[1] = GeneralMeituFragment.getInstance();
                    break;
                case 2:
                    fragments[2] = OverviewNewsFragment.getInstance();
                    break;
            }


        }
        return fragments[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
