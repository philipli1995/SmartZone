package com.philipli.smartzone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.philipli.smartzone.R;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.bean.NewsSummary;
import com.philipli.smartzone.ui.books.BookDetailActivity;
import com.philipli.smartzone.ui.news.NewsDetailActivity;
import com.philipli.smartzone.util.GlideUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by philipli on 2017/12/6.
 */

public class NewsAdapter extends GeneralAdapter {

    private Context mContext;
    private List<NewsSummary> mList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder getChildViewHolder(ViewGroup parent) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
    }

    public NewsAdapter(Context context, List<NewsSummary> list) {
        this.mContext = context;
        this.mList.addAll(list);
    }

    public void updateList(List<NewsSummary> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof NewsViewHolder) {
            if (!mList.isEmpty()) {
                NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
                newsViewHolder.setImage(mList.get(position).getImgsrc(), mContext);
                newsViewHolder.setTitle(mList.get(position).getTitle());
                newsViewHolder.setSummary(mList.get(position).getDigest());
                newsViewHolder.setAuthor(mList.get(position).getSource());
                newsViewHolder.setTime(mList.get(position).getPtime());

                newsViewHolder.setOnClickListener(mList.get(position).getPostid());

            }
        }
    }
    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    private class NewsViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title;
        private TextView summary;
        private TextView author;
        private TextView time;

        private View view;

        public NewsViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.news_list_image);
            title = itemView.findViewById(R.id.news_list_title);
            summary = itemView.findViewById(R.id.news_list_summary);
            author = itemView.findViewById(R.id.news_list_source);
            time = itemView.findViewById(R.id.news_list_time);
            view = itemView;
        }

        public void setImage(String url, Context context) {
            Glide.with(context)
                    .load(url)
                    .apply(GlideUtil.getRequestOptions())
                    .transition(withCrossFade(500))
                    .into(image);
        }

        public void setOnClickListener(String id) {

            view.setOnClickListener(aLong-> NewsDetailActivity.start(mContext, id, image));

        }

        public void setTitle(String name) {
            title.setText(name);
        }

        public void setSummary(String rate) {
            summary.setText(rate);
        }

        public void setAuthor(String rate) {
            author.setText(rate);
        }
        public void setTime(String rate) {
            time.setText(rate);
        }
    }
}
