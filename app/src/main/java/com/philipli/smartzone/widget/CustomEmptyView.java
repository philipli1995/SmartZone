package com.philipli.smartzone.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.philipli.smartzone.R;

/**
 * Created by philipli on 2017/12/6.
 */

public class CustomEmptyView extends FrameLayout{

    private ImageView image;
    private TextView text;

    public CustomEmptyView(@NonNull Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_empty_layout, this);
        image = view.findViewById(R.id.empty_img);
        text = view.findViewById(R.id.empty_text);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_empty_layout, this);
        image = view.findViewById(R.id.empty_img);
        text = view.findViewById(R.id.empty_text);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_empty_layout, this);
        image = view.findViewById(R.id.empty_img);
        text = view.findViewById(R.id.empty_text);
    }

    public CustomEmptyView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.custom_empty_layout, this);
        image = view.findViewById(R.id.empty_img);
        text = view.findViewById(R.id.empty_text);
    }

    public void setImage(int resId) {
        image.setImageResource(resId);
    }

    public void setText(String s) {
        text.setText(s);
    }



}
