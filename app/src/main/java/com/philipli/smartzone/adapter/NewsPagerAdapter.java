package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

import com.philipli.smartzone.R;
import com.philipli.smartzone.ui.books.GeneralBookFragment;
import com.philipli.smartzone.ui.news.GeneralNewsFragment;
import com.philipli.smartzone.util.CommonUtil;
import com.philipli.smartzone.util.ConstantUtil;
import com.philipli.smartzone.util.DebugUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by philipli on 2017/10/18.
 */

public class NewsPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES;
    private final String[] TYPES;
    private final String[] IDS;
    private List<Integer> savedChannels;
    private Fragment[] fragments;
//    private FragmentManager fm;

    public NewsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
//        this.fm = fm;
        DebugUtil.debug("enter creator");
        TITLES = context.getResources().getStringArray(R.array.news_tabs);
        TYPES = context.getResources().getStringArray(R.array.news_api_type);
        IDS = context.getResources().getStringArray(R.array.news_api_id);
        savedChannels = CommonUtil.getMyChannels();
        fragments = new Fragment[savedChannels.size()];
//        for (int i = 0; i < savedChannels.size(); i++) {
//            int realPosition = savedChannels.get(i);
//            fragments[i] = GeneralNewsFragment.getInstance(TITLES[realPosition], TYPES[realPosition], IDS[realPosition]);
//        }
//        setFragments(fragments);
    }

//    private void setFragments(Fragment[] fragments) {
//
//        if (this.fragments != null) {
//            FragmentTransaction fragmentTransaction = fm.beginTransaction();
//            for (Fragment f : this.fragments) {
//                fragmentTransaction.remove(f);
//            }
//            fragmentTransaction.commit();
//            fm.executePendingTransactions();
//
//        }
//        this.fragments = fragments;
//        notifyDataSetChanged();
//
//    }


    @Override
    public Fragment getItem(int position) {
//        DebugUtil.debug("enter getItem" + position);
        int realPosition = savedChannels.get(position);
        if (fragments[position] == null) {

            fragments[position] = GeneralNewsFragment.getInstance(TITLES[realPosition], TYPES[realPosition], IDS[realPosition]);

        }
        return fragments[position];
    }

    @Override
    public int getCount() {
//        DebugUtil.debug("enter getCount");
        return savedChannels.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
//        DebugUtil.debug("enter getPageTitle" + position);
        return TITLES[savedChannels.get(position)];
    }

    /*
    用于调整完频道后更新adapter，但是更新经常和点击返回页面顶部出问题，已经启用改成刷新整个fragment
     */
//    public void updateAdapter() {
//        List<Integer> list = new ArrayList<>(savedChannels);
//        savedChannels = CommonUtil.getMyChannels();
//        Fragment[] newFragments = new Fragment[savedChannels.size()];
//        for (int i = 0; i < savedChannels.size(); i++) {
//            int realPosition = savedChannels.get(i);
//            if (list.contains(realPosition)) {
//                newFragments[i] = fragments[list.indexOf(realPosition)];
//            }
//            else {
//                newFragments[i] = GeneralNewsFragment.getInstance(TITLES[realPosition], TYPES[realPosition], IDS[realPosition]);
//            }
//        }
//        setFragments(newFragments);
//
//    }

//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }
}
