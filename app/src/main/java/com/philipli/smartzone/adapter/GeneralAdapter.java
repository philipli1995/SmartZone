package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.philipli.smartzone.R;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by philipli on 2018/1/2.
 */

public abstract class GeneralAdapter extends RecyclerView.Adapter {

    public final static int HEADER = 0;
    public final static int CONTENT = 1;
    public final static int FOOTER = 2;

    public final static int LOAD_IDLE = 0;
    public final static int LOAD_LOADING = 1;
    public final static int LOAD_PULL_UP = 2;
    public final static int LOAD_END = 3;


    private int status = 0;

    private GeneralAdapter.FooterViewHolder viewHolder;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case CONTENT:
                return getChildViewHolder(parent);
            case FOOTER:
                return new GeneralAdapter.FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    public abstract RecyclerView.ViewHolder getChildViewHolder(ViewGroup parent);

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GeneralAdapter.FooterViewHolder) {
            viewHolder = (GeneralAdapter.FooterViewHolder)holder;
        }
    }



    public void onNetworkFailed() {
        if (viewHolder == null) {
            return;
        }
        viewHolder.setProgressBarVisibility(false);
        viewHolder.setText(R.string.loading_failed);
    }


    public void onNetworkWorked() {
        if (viewHolder == null) {
            return;
        }
        viewHolder.setProgressBarVisibility(true);
        viewHolder.setText(R.string.loading_now);
    }


    public int getStatus() {
        return status;
    }


    @Override
    public int getItemViewType(int position) {
        if (position + 1== getItemCount()) {
            return FOOTER;
        }
        else {
            return CONTENT;
        }
    }

    public void updateStatus(int status) {
        this.status = status;
        this.notifyDataSetChanged();
    }


    private class FooterViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ProgressBar progressBar;

        public FooterViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.load_hint);
            progressBar = itemView.findViewById(R.id.progress_bar);
        }

        public void setText(int resId) {
            textView.setText(resId);
        }

        public void setProgressBarVisibility(boolean visible) {
            if (visible) {
                progressBar.setVisibility(View.VISIBLE);
            }
            else {
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    }

}
