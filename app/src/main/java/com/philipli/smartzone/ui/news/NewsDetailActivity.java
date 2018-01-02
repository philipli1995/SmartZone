package com.philipli.smartzone.ui.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.bean.BookDetailBean;
import com.philipli.smartzone.bean.NewsDetail;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.util.BitmapUtil;
import com.philipli.smartzone.util.GlideUtil;
import com.philipli.smartzone.widget.URLImageGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by philipli on 2017/12/18.
 */

public class NewsDetailActivity extends RxBaseActivity {


    private static final String INTENT_NAME = "id";
    private static final String INTENT_IMG = "img";

    @BindView(R.id.toolbar)
    Toolbar mToolBar;
    @BindView(R.id.news_detail_img)
    ImageView mBgImage;
    @BindView(R.id.news_detail_source)
    TextView mNewsSource;
    @BindView(R.id.news_detail_body)
    TextView mNewsBody;




    private String id;
    private NewsDetail newsDetail;
    private Bitmap bitmap;


    @Override
    public int getLayoutId() {
        return R.layout.activity_news_detail;
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

//        mToolBar.setOverflowIcon(getResources().getDrawable(R.drawable.actionbar_more));
        mToolBar.setNavigationOnClickListener(aLong -> onBackPressed());
        mToolBar.setTitleTextAppearance(this, R.style.ToolBar_Title);




    }



    @Override
    public void initViews(Bundle savedInstanceState) {

        Bundle bundle = getIntent().getExtras();

        id = bundle.getString(INTENT_NAME);
        bitmap = (Bitmap) bundle.getParcelable(INTENT_IMG);
        mBgImage.setImageBitmap(bitmap);

        loadData();

    }


    @Override
    public void loadData() {

        RetrofitConfig.getNeteaseAPI().getNewsDetail(id)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Map<String, NewsDetail>>() {
                    @Override
                    public void accept(Map<String, NewsDetail> stringNewsDetailMap) throws Exception {
                        newsDetail = stringNewsDetailMap.entrySet().iterator().next().getValue();
                        finishTask();
                    }
                }, throwable -> initEmptyView());


    }

    public void finishTask() {


        mNewsSource.setText(newsDetail.getSource());
        mToolBar.setTitle(newsDetail.getTitle());

        int imgSize = newsDetail.getImg().size();

        newsDetail.setBody(changeNewsBody(newsDetail.getImg(), newsDetail.getBody()));

        if (imgSize >= 2 && newsDetail.getBody() != null) {
            mNewsBody.setText(Html.fromHtml(newsDetail.getBody(), new URLImageGetter(getApplicationContext(), mNewsBody, newsDetail.getImg()), null));
        } else {
            mNewsBody.setText(Html.fromHtml(newsDetail.getBody()));
        }




    }

    private void initEmptyView() {
        mNewsBody.setText("抱歉，由于网络原因本条新闻无法观看...(ಥ_ಥ)");
        mNewsBody.setGravity(Gravity.CENTER);
    }

    public static void start(Context context, String id, ImageView imageView) {

        Intent intent = new Intent(context, NewsDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(INTENT_NAME, id);

        Bitmap bitmap = BitmapUtil.drawable2Bitmap(imageView.getDrawable());
        bundle.putParcelable(INTENT_IMG, bitmap);

        intent.putExtras(bundle);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context, imageView, context.getString(R.string.anime_news_badger));
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }

    private String changeNewsBody(List<NewsDetail.ImgBean> imgSrcs, String newsBody) {
        for (int i = 0; i < imgSrcs.size(); i++) {
            String oldChars = "<!--IMG#" + i + "-->";
            String newChars;
            if (i == 0) {
                newChars = "";
            } else {
                newChars = "<img src=\"" + imgSrcs.get(i).getSrc() + "\" />";
            }
            newsBody = newsBody.replace(oldChars, newChars);

        }
        return newsBody;
    }

    @Override
    protected void onDestroy() {
        mBgImage.setImageBitmap(null);
        BitmapUtil.bitmapRecycle(bitmap);
        super.onDestroy();
    }
}

