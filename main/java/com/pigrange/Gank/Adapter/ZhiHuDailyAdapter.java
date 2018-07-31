package com.pigrange.Gank.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pigrange.Gank.Model.BaseResult;
import com.pigrange.Gank.Model.ZhiHuDaily;
import com.pigrange.Gank.R;
import com.pigrange.Gank.Utils.JumpUtil;
import com.pigrange.Gank.BaseClass.BaseAdapter;

import java.util.List;

public class ZhiHuDailyAdapter extends BaseAdapter<ZhiHuDailyAdapter.DailyHolder>{
    private List<BaseResult> mStoriesBeanList;
    private Context mContext;

    public ZhiHuDailyAdapter(List<BaseResult> list) {
        super(list);
        this.mStoriesBeanList = list;
    }

    @Override
    public BaseHolder onChildCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.zhihu_item, parent, false);
        final DailyHolder holder = new DailyHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                ZhiHuDaily.StoriesBean storiesBean = (ZhiHuDaily.StoriesBean) mStoriesBeanList.get(pos);
                int id = storiesBean.getId();
                int date = storiesBean.getDate();
                JumpUtil.startZhiHuNews(mContext, String.valueOf(id), date);
            }
        });
        return holder;
    }

    @Override
    public void onChildBindViewHolder(@NonNull DailyHolder holder, int position) {
        ZhiHuDaily.StoriesBean storiesBean = (ZhiHuDaily.StoriesBean) mStoriesBeanList.get(position);
        Glide.with(mContext)
                .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.loading))
                .load(storiesBean.getImages().get(0))
                .into(holder.zhihu_image);
        holder.zhihu_title.setText(storiesBean.getTitle());
    }

    static class DailyHolder extends BaseAdapter.BaseHolder {
        private ImageView zhihu_image;
        private TextView zhihu_title;

        DailyHolder(View itemView) {
            super(itemView);
            this.zhihu_image = itemView.findViewById(R.id.zhihu_item_image);
            this.zhihu_title = itemView.findViewById(R.id.zhihu_title);
        }
    }

}
