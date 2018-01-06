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
import com.philipli.smartzone.ui.books.BookDetailActivity;
import com.philipli.smartzone.ui.meitu.MeituActivity;
import com.philipli.smartzone.util.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by philipli on 2017/12/6.
 */

public class MeituAdapter extends GeneralAdapter {

    private Context mContext;
    private ArrayList<String> mList = new ArrayList<>();


    @Override
    public RecyclerView.ViewHolder getChildViewHolder(ViewGroup parent) {
        return new MeituViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meitu, parent, false));
    }

    public MeituAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.mList.addAll(list);
    }

    public void updateList(List<String> list) {
        this.mList.clear();
        this.mList.addAll(list);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if (holder instanceof MeituViewHolder) {
            if (!mList.isEmpty()) {
                MeituViewHolder meituViewHolder = (MeituViewHolder) holder;
                meituViewHolder.setImage(mList.get(position), mContext);
                meituViewHolder.setOnClickListener(position, mList);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size() + 1;
    }


    private class MeituViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;

        public MeituViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.meitu_image);
        }

        public void setImage(String url, Context context) {
            Glide.with(context)
                    .load(url)
                    .transition(withCrossFade(500))
                    .apply(GlideUtil.getRequestOptions())
                    .into(image);
        }

        public void setOnClickListener(int position, ArrayList<String> list) {

            image.setOnClickListener(aLong -> MeituActivity.start(mContext, position, list));

        }

    }
}
