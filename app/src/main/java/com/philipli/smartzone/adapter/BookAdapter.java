package com.philipli.smartzone.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class BookAdapter extends GeneralAdapter {


    private int status = 0;

    private Context mContext;
    private List<BooksBean.BookBean> mList = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder getChildViewHolder(ViewGroup parent) {
        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_card, parent, false));
    }

    public BookAdapter(Context context, List<BooksBean.BookBean> list) {
        this.mContext = context;
        this.mList.addAll(list);
    }

    public void updateList(List<BooksBean.BookBean> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof BookViewHolder) {
            if (!mList.isEmpty()) {
                BookViewHolder bookViewHolder = (BookViewHolder) holder;
                bookViewHolder.setImage(mList.get(position).getImages().getLarge(), mContext);
                bookViewHolder.setBookName(mList.get(position).getTitle());
                bookViewHolder.setRate(mList.get(position).getRating().getAverage());
                bookViewHolder.setOnClickListener(mList.get(position).getId());
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    private class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView bookName;
        private TextView bookRate;

        public BookViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.book_image);
            bookName = itemView.findViewById(R.id.book_name);
            bookRate = itemView.findViewById(R.id.book_rate);
        }

        public void setImage(String url, Context context) {
            Glide.with(context)
                    .load(url)
                    .apply(GlideUtil.getRequestOptions())
                    .transition(withCrossFade(500))
                    .into(image);
        }

        public void setOnClickListener(String id) {

            image.setOnClickListener(aLong-> BookDetailActivity.start(mContext, id, image));

        }

        public void setBookName(String name) {
            bookName.setText(name);
        }

        public void setRate(String rate) {
            bookRate.setText(rate);
        }
    }
}
