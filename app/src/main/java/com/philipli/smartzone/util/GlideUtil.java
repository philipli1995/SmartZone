package com.philipli.smartzone.util;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.philipli.smartzone.R;

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

}
