package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.ui.books.BookDetailActivity;
import com.philipli.smartzone.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by philipli on 2017/12/6.
 */

public class ChannelOptionAdapter extends RecyclerView.Adapter {

    private final String[] TITLES;
    private List<Integer> channels = new ArrayList<>();
    private OnItemClickListener listener;

    public ChannelOptionAdapter(Context context, List<Integer> oldChannels) {
        TITLES = context.getResources().getStringArray(R.array.news_tabs);
        channels.addAll(oldChannels);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void addItem(int item) {
        channels.add(item);
    }

    private void setOnClickListener(RecyclerView.ViewHolder holder, int position) {
        if (listener == null) {
            return;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position, v);
            }
        });
    }

    public List<Integer> getChannels() {
        return channels;
    }


    public int removeItem(int position) {
        return channels.remove(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ChannelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_channel, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ChannelViewHolder)holder).setText(TITLES[channels.get(position)]);
        setOnClickListener(holder, position);

    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public interface OnItemClickListener {
        public void onClick(int position, View view);
    }

    private class ChannelViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        View view;


        public ChannelViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            textView = (TextView) itemView.findViewById(R.id.news_channel_tv);
        }

        public void setText(String text) {
            textView.setText(text);
        }


    }
}
