package com.pigrange.rxjavaretrofittest.Model;

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
import com.pigrange.rxjavaretrofittest.R;

import java.util.List;

public class MeiZhiAdapter extends RecyclerView.Adapter<MeiZhiAdapter.ViewHolder> {
    private List<GanHuo.Result> mResultList;
    private Context mContext;
    private adapterItemOnclickListener mAdapterItemOnclickListener;

    public MeiZhiAdapter(List<GanHuo.Result> results) {
        mResultList = results;
    }

    public void setAdapterItemOnclickListener(adapterItemOnclickListener listener) {
        mAdapterItemOnclickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater
                .from(mContext)
                .inflate(R.layout.singeimage_view, parent, false);

        final ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = mResultList.get(position);
                Intent intent = new Intent(mContext, ShowImage.class);
                intent.putExtra("url", result.getUrl());
                mAdapterItemOnclickListener.onclick(intent);
                //todo 查看大图
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GanHuo.Result mResult = mResultList.get(position);
        Glide.with(mContext)
                .load(mResult.getUrl())
                .apply(new RequestOptions()
                        .centerCrop()
                        .placeholder(R.drawable.loading))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.mImageView);
        }
    }

    public interface adapterItemOnclickListener {
        void onclick(Intent intent);
    }

}
