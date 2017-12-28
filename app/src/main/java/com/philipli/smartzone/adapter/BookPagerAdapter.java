package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.philipli.smartzone.R;
import com.philipli.smartzone.test.SimpleFragment;
import com.philipli.smartzone.ui.books.GeneralBookFragment;


/**
 * Created by philipli on 2017/10/18.
 */

public class BookPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;

    private Fragment[] fragments;

    public BookPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        TITLES = context.getResources().getStringArray(R.array.book_tabs);
        fragments = new Fragment[TITLES.length];

    }

    @Override
    public Fragment getItem(int position) {
        if (fragments[position] == null) {

            fragments[position] = GeneralBookFragment.getInstance(TITLES[position]);

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
