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

public class NewsAdapter extends RecyclerView.Adapter {

    public final static int NEWS_HEADER = 0;
    public final static int NEWS_CONTENT = 1;
    public final static int NEWS_FOOTER = 2;

    public final static int LOAD_IDLE = 0;
    public final static int LOAD_LOADING = 1;
    public final static int LOAD_PULL_UP = 2;
    public final static int LOAD_END = 3;


    private int status = 0;

    private Context mContext;
    private List<NewsSummary> mList = new ArrayList<>();



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case NEWS_CONTENT:
                return new NewsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news_list, parent, false));
            case NEWS_FOOTER:
                return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
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


        if (holder instanceof NewsViewHolder) {
            if (!mList.isEmpty()) {
                NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
                newsViewHolder.setImage(mList.get(position).getImgsrc(), mContext);
                newsViewHolder.setTitle(mList.get(position).getTitle());
                newsViewHolder.setSummary(mList.get(position).getDigest());
                newsViewHolder.setAuthor(mList.get(position).getSource());
                newsViewHolder.setTime(mList.get(position).getPtime());

                newsViewHolder.setOnClickListener(mList.get(position).getPostid(), mList.get(position).getImgsrc());

            }
        }
        else if (holder instanceof FooterViewHolder) {

        }
    }

    public int getStatus() {
        return status;
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1== getItemCount()) {
            return NEWS_FOOTER;
        }
        else {
            return NEWS_CONTENT;
        }
    }

    public void updateStatus(int status) {
        this.status = status;
        this.notifyDataSetChanged();
    }

    private class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
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

        public void setOnClickListener(String id, String img) {

            //待实现
            view.setOnClickListener(aLong-> NewsDetailActivity.start(mContext, id, img));

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
