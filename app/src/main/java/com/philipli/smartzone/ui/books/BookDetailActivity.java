package com.philipli.smartzone.ui.books;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.transition.ArcMotion;
import android.transition.Fade;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.base.RxBaseActivity;
import com.philipli.smartzone.bean.BookDetailBean;
import com.philipli.smartzone.network.RetrofitConfig;
import com.philipli.smartzone.util.GlideUtil;
import com.philipli.smartzone.widget.CustomChangeBounds;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by philipli on 2017/12/18.
 */

public class BookDetailActivity extends RxBaseActivity {


    private static final String INTENT_NAME = "id";
    @BindView(R.id.book_image)
    ImageView mBookImage;
    @BindView(R.id.author)
    TextView mAuthor;
    @BindView(R.id.rate)
    TextView mRate;
    @BindView(R.id.time)
    TextView mTime;
    @BindView(R.id.publisher)
    TextView mPublisher;
    @BindView(R.id.book_intro)
    TextView mBookIntro;
    @BindView(R.id.author_info)
    TextView mAuthorInfo;
    @BindView(R.id.book_index)
    TextView mBookIndex;
    @BindView(R.id.book_bg_image)
    ImageView mBgImage;
    @BindView(R.id.tb_base_title)
    Toolbar mToolBar;
    @BindView(R.id.book_headline)
    TextView mHeadline;

    private String id;
    private BookDetailBean bookBean;


    @Override
    public int getLayoutId() {
        return R.layout.activity_book_detail;
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
        mToolBar.setSubtitleTextAppearance(this, R.style.Toolbar_SubTitle);



    }



    @Override
    public void initViews(Bundle savedInstanceState) {

        id = (String) getIntent().getSerializableExtra(INTENT_NAME);

        loadData();

    }


    @Override
    public void loadData() {

        RetrofitConfig.getDoubanAPI().getBookDetail(id)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BookDetailBean>() {
                               @Override
                               public void accept(BookDetailBean bookDetailBean) throws Exception {
                                   bookBean = bookDetailBean;
                                   finishTask();
                               }
                           }
                );


    }

    public void finishTask() {

        mAuthor.setText(bookBean.getAuthor().toString().substring(1, bookBean.getAuthor().toString().length() - 1));
        mAuthorInfo.setText(bookBean.getAuthor_intro());
        mBookIndex.setText(bookBean.getCatalog());
        mBookIntro.setText(bookBean.getSummary());
        mRate.setText(bookBean.getRating().getAverage());
        mRate.setTextColor(getResources().getColor(R.color.red_trans));
        mTime.setText(bookBean.getPubdate());
        mPublisher.setText(bookBean.getPublisher());
//        mHeadline.setText(bookBean.getTitle());
        mToolBar.setTitle(bookBean.getTitle());
        mToolBar.setSubtitle(mAuthor.getText());


        Glide.with(this)
                .load(bookBean.getImages().getLarge())
                .apply(GlideUtil.getRequestOptions())
                .transition(withCrossFade(500))
                .into(mBookImage);

        Glide.with(this)
                .load(bookBean.getImages().getLarge())
                .apply(GlideUtil.getBgOptions())
                .transition(withCrossFade(500))
                .into(mBgImage);

    }

    public static void start(Context context, String id) {

        Intent intent = new Intent(context, BookDetailActivity.class);
        intent.putExtra(INTENT_NAME, id);
        ActivityCompat.startActivity(context, intent, null);
    }

}

