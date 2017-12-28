package com.philipli.smartzone.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.philipli.smartzone.R;
import com.philipli.smartzone.ui.books.GeneralBookFragment;
import com.philipli.smartzone.ui.meitu.MeituActivity;
import com.philipli.smartzone.util.GlideUtil;

import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by philipli on 2017/10/18.
 */
public class MeituPagerAdapter extends PagerAdapter {

    LayoutInflater inflater;
    List<String> imgurls;
    Activity activity;

    public MeituPagerAdapter(LayoutInflater inflater, Activity activity, List<String> urls) {
        this.inflater = inflater;
        this.activity = activity;
        this.imgurls = urls;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.item_metu, container, false);
        final PhotoView zoom_image_view = (PhotoView) view.findViewById(R.id.meitu_image);
        final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
        String imageUrl = imgurls.get(position);

        spinner.setVisibility(View.VISIBLE);
        spinner.setClickable(false);
        Glide.with(activity)
                .load(imageUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        spinner.setVisibility(View.INVISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        /**这里应该是加载成功后图片的高*/
                        int height = zoom_image_view.getHeight();
                        DisplayMetrics dm = new DisplayMetrics();
                        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
                        int wHeight = dm.heightPixels;
                        if (height > wHeight) {
                            zoom_image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        } else {
                            zoom_image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                        }
                        spinner.setVisibility(View.INVISIBLE);
                        return false;
                    }
                })
                .apply(GlideUtil.getMeituRequestOptions())
                .transition(withCrossFade(700))
                .into(zoom_image_view);

//        zoom_image_view.setOnPhotoTapListener(ViewBigImageActivity.this);
        container.addView(view, 0);
        return view;
    }

    @Override
    public int getCount() {
        if (imgurls == null || imgurls.size() == 0) {
            return 0;
        }
        return imgurls.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }


}