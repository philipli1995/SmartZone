package com.philipli.smartzone.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.philipli.smartzone.app.SmartZoneApp;
import com.philipli.smartzone.bean.NewsDetail;
import com.philipli.smartzone.util.DebugUtil;
import com.philipli.smartzone.util.DensityUtil;
import com.philipli.smartzone.util.GlideUtil;

import java.net.URL;
import java.util.List;

/**
 * Created by philipli on 2017/12/25.
 */

public class URLImageGetter implements Html.ImageGetter {

    Context mContext;
    TextView mTextView;
    int actX;
    private List<NewsDetail.ImgBean> imgurls;
    int position;


    public URLImageGetter(Context context, TextView textView, List<NewsDetail.ImgBean> imgurls) {
        mContext = context;
        mTextView = textView;
        actX = textView.getWidth() * 2 / 3;
        this.imgurls = imgurls;
        position = 0;
    }

//    public Drawable getDrawable(String source) {
//        Drawable drawable = null;
//        URL url;
//        try {
//            url = new URL(source);
//            drawable = Drawable.createFromStream(url.openStream(), "");  //获取网路图片
//        } catch (Exception e) {
//            return null;
//        }
//        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//        return drawable;
//    }

    @Override
    public Drawable getDrawable(String source) {
        URLDrawable urlDrawable = new URLDrawable();
        Glide.with(mContext)
                .asBitmap()
                .load(source)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        int x = resource.getWidth();
                        int y = resource.getHeight();
                        // 由于不稳定产生错误暂时注掉
//                        if (x > actX) {
//                            //进行等比例缩放程序2
//                            Matrix matrix = new Matrix();
//                            matrix.postScale((float) (actX * 1.00 / x), (float) (actX * 1.00 / x));
//                            DebugUtil.debug("x",x+"");
//                            DebugUtil.debug("y",y+"");
//                            //长和宽放大缩小的比例
//                            resource = Bitmap.createBitmap(resource, 0, 0, x, y, matrix, true);
//                        }
                        urlDrawable.bitmap = resource;
                        urlDrawable.setBounds(0, 0, resource.getWidth(), resource.getHeight());
                        mTextView.invalidate();
                        mTextView.setText(mTextView.getText()); // 解决图文重叠
                    }
                });

        return urlDrawable;
    }

    private class URLDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

}

