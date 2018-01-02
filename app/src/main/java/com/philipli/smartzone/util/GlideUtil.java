package com.philipli.smartzone.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestOptions;
import com.philipli.smartzone.R;

import java.io.File;
import java.util.concurrent.ExecutionException;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

/**
 * Created by philipli on 2017/12/8.
 */

public class GlideUtil {



    public static RequestOptions getRequestOptions() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_image_loading)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        return requestOptions;
    }

    public static RequestOptions getMeituRequestOptions() {
        RequestOptions requestOptions = new RequestOptions()
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        return requestOptions;
    }

    public static RequestOptions getBgOptions() {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.ic_image_loading)
                .centerCrop()
                .bitmapTransform(new BlurTransformation(23, 4))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        return requestOptions;
    }

    public static String getImagePath(String imgUrl, Context context) {
        String path = null;
        FutureTarget<File> future = Glide.with(context)
                .download(imgUrl)
                .submit();
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }

}
