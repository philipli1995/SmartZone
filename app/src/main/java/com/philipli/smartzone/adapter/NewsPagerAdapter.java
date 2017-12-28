package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.philipli.smartzone.R;
import com.philipli.smartzone.ui.books.GeneralBookFragment;
import com.philipli.smartzone.ui.news.GeneralNewsFragment;


/**
 * Created by philipli on 2017/10/18.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;
    private final String[] TYPES;
    private final String[] IDS;
    private Fragment[] fragments;

    public NewsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.news_tabs);
        TYPES = context.getResources().getStringArray(R.array.news_api_type);
        IDS = context.getResources().getStringArray(R.array.news_api_id);

        fragments = new Fragment[TITLES.length];

    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {

            fragments[position] = GeneralNewsFragment.getInstance(TITLES[position], TYPES[position], IDS[position]);

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
