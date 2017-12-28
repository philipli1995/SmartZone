package com.philipli.smartzone.ui.meitu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.adapter.MeituAdapter;
import com.philipli.smartzone.adapter.MeituPagerAdapter;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.bean.BookDetailBean;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by philipli on 2017/12/18.
 */

public class MeituActivity extends RxBaseActivity {


    private static final String INTENT_POSITION = "position";
    private static final String INTENT_IMGURL = "imgurl";

    @BindView(R.id.image_viewpager_text)
    TextView mTextView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;


    private List<String> urls;
    private MeituPagerAdapter meituAdapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_meitu_view_pager;
    }

    @Override
    public void initToolBar() {

    }


    @Override
    public void initViews(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();

        int position = bundle.getInt(INTENT_POSITION);
        urls = bundle.getStringArrayList(INTENT_IMGURL);

        meituAdapter = new MeituPagerAdapter(getLayoutInflater(), this, urls);
        String text = (position+1) + "/" + urls.size();
        mTextView.setText(text);

        mViewPager.setAdapter(meituAdapter);
        mViewPager.setCurrentItem(position);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String text = (position+1) + "/" + urls.size();
                mTextView.setText(text);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });




    }


    @Override
    public void loadData() {


    }

    public void finishTask() {


    }

    public static void start(Context context, int position, ArrayList<String> imgUrls) {

        Intent intent = new Intent(context, MeituActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt(INTENT_POSITION, position);
        bundle.putStringArrayList(INTENT_IMGURL, imgUrls);
        intent.putExtras(bundle);
        ActivityCompat.startActivity(context, intent, null);
    }

}

