package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pigrange.rxjavaretrofittest.Model.ZhiHuDaily;
import com.pigrange.rxjavaretrofittest.R;
import com.pigrange.rxjavaretrofittest.Utils.JumpUtil;

import java.util.List;

public class ZhiHuDailyAdapter extends RecyclerView.Adapter<ZhiHuDailyAdapter.DailyHolder> {
    private List<ZhiHuDaily.StoriesBean> mStoriesBeanList;
    private Context mContext;
    private boolean isHasFooter;
    private final int TYPE_FOOTER = 0X1111;
    private View footer = null;

    public ZhiHuDailyAdapter(List<ZhiHuDaily.StoriesBean> list) {
        this.mStoriesBeanList = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mStoriesBeanList.size() && isHasFooter) {
            return TYPE_FOOTER;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public DailyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        if (viewType == TYPE_FOOTER){
            return new DailyHolder(footer);
        }

        View v = LayoutInflater.from(mContext).inflate(R.layout.zhihu_item, parent, false);
        final DailyHolder holder = new DailyHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                int id = mStoriesBeanList.get(pos).getId();
                JumpUtil.startZhiHuNews(mContext, String.valueOf(id));
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyHolder holder, int position) {
        if (position == mStoriesBeanList.size()){
            return;
        }

        ZhiHuDaily.StoriesBean storiesBean = mStoriesBeanList.get(position);
        Glide.with(mContext)
                .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading))
                .load(storiesBean.getImages().get(0))
                .into(holder.zhihu_image);
        holder.zhihu_title.setText(storiesBean.getTitle());
    }

    @Override
    public int getItemCount() {
        if (isHasFooter){
            return mStoriesBeanList.size()+1;
        }
        return mStoriesBeanList.size();
    }

    public View getFooter() {
        return footer;
    }

    public void setFooter(View footer) {
        isHasFooter = true;
        this.footer = footer;
        notifyDataSetChanged();
    }

    static class DailyHolder extends RecyclerView.ViewHolder {
        private ImageView zhihu_image;
        private TextView zhihu_title;

        DailyHolder(View itemView) {
            super(itemView);
            this.zhihu_image = itemView.findViewById(R.id.zhihu_item_image);
            this.zhihu_title = itemView.findViewById(R.id.zhihu_title);
        }

        DailyHolder(View itemView, Boolean isHasFooter) {
            super(itemView);
        }
    }
}
