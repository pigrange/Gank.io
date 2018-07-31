package com.pigrange.Gank.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.pigrange.Gank.Activity.ShowMeiZhiImage;
import com.pigrange.Gank.BaseClass.BaseAdapter;
import com.pigrange.Gank.Model.BaseResult;
import com.pigrange.Gank.Model.GanHuo;
import com.pigrange.Gank.R;

import java.util.List;

public class MeiZhiAdapter extends BaseAdapter<MeiZhiAdapter.MeiZhiViewHolder> {
    private Context mContext;
    private List<BaseResult> mResultList;

    public MeiZhiAdapter(List<BaseResult> results) {
        super(results);
        mResultList = results;
    }


    @Override
    public BaseHolder onChildCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.meizhi_item, parent, false);
        final MeiZhiViewHolder holder = new MeiZhiViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = (GanHuo.Result) mResultList.get(position);
                Intent intent = new Intent(mContext, ShowMeiZhiImage.class);
                intent.putExtra("url", result.getUrl());
                OnItemClick(intent);
            }
        });
        return holder;
    }

    @Override
    public void onChildBindViewHolder(@NonNull MeiZhiViewHolder holder, int position) {
        GanHuo.Result mResult = (GanHuo.Result) mResultList.get(position);
        Glide.with(mContext)
                .load(mResult.getUrl())
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.loading))
                .into(holder.mImageView);

    }

    static class MeiZhiViewHolder extends BaseAdapter.BaseHolder {
        private ImageView mImageView;

        MeiZhiViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.meizhi_item_imageView);
        }
    }

}
