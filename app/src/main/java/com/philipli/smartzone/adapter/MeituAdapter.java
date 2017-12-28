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

public class MeituAdapter extends RecyclerView.Adapter {

    public final static int Meitu_HEADER = 0;
    public final static int Meitu_CONTENT = 1;
    public final static int Meitu_FOOTER = 2;

    public final static int LOAD_IDLE = 0;
    public final static int LOAD_LOADING = 1;
    public final static int LOAD_PULL_UP = 2;
    public final static int LOAD_END = 3;


    private int status = 0;

    private Context mContext;
    private ArrayList<String> mList = new ArrayList<>();



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Meitu_CONTENT:
                return new MeituViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meitu, parent, false));
            case Meitu_FOOTER:
                return new FooterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false));
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
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


        if (holder instanceof MeituViewHolder) {
            if (!mList.isEmpty()) {
                MeituViewHolder meituViewHolder = (MeituViewHolder) holder;
                meituViewHolder.setImage(mList.get(position), mContext);
                meituViewHolder.setOnClickListener(position, mList);
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
            return Meitu_FOOTER;
        }
        else {
            return Meitu_CONTENT;
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
