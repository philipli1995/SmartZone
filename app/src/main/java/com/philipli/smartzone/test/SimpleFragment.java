package com.philipli.smartzone.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.philipli.smartzone.R;


/**
 * Created by philipli on 2017/10/19.
 */

public class SimpleFragment extends android.support.v4.app.Fragment {

    private String mTitle;

    public static SimpleFragment getInstance(String title) {
        SimpleFragment sf = new SimpleFragment();
        sf.mTitle = title;
        return sf;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_tester_page, null);
        TextView textView = (TextView) v.findViewById(R.id.text);
        textView.setText(this.mTitle);
        return v;
    }
}
