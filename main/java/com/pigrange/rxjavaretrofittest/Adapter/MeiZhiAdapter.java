package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pigrange.rxjavaretrofittest.Activity.ShowImage;
import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.R;

import java.util.List;

public class MeiZhiAdapter extends BaseAdapter<MeiZhiAdapter.MeiZhiViewHolder> {
    private Context mContext;
    private List<GanHuo.Result> mResultList;

    public MeiZhiAdapter(List<GanHuo.Result> results) {
        super(results);
        mResultList = results;
    }

    @NonNull
    @Override
    public MeiZhiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.meizhi_item, parent, false);
        final MeiZhiViewHolder holder = new MeiZhiViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = mResultList.get(position);
                Intent intent = new Intent(mContext, ShowImage.class);
                intent.putExtra("url", result.getUrl());
                onItemClick(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MeiZhiViewHolder holder, int position) {
        GanHuo.Result mResult = mResultList.get(position);
        Glide.with(mContext)
                .load(mResult.getUrl())
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.loading))
                .into(holder.mImageView);
    }

    static class MeiZhiViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        MeiZhiViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.meizhi_item_imageView);
        }
    }

}
