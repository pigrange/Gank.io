package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pigrange.rxjavaretrofittest.Activity.WebViewActivity;
import com.pigrange.rxjavaretrofittest.BaseClass.BaseAdapter;
import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.R;

import java.util.List;

public class TodayGankAdapter extends BaseAdapter<TodayGankAdapter.TodayViewHolder> {
    private List<GanHuo.Result> mResultList;
    private Context mContext;

    public TodayGankAdapter(List<GanHuo.Result> results) {
        super(results);
        this.mResultList = results;
    }

    @NonNull
    @Override
    public TodayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.show_today, parent, false);
        final TodayViewHolder holder = new TodayViewHolder(v);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = mResultList.get(position);
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", result.getUrl());
                onItemClick(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodayViewHolder holder, int position) {
        GanHuo.Result result = mResultList.get(position);
        if (position == 0){
            showCategory(true,holder.category);
        }else {
            if (mResultList.get(position).getType().equals(mResultList.get(position-1).getType())){
                showCategory(false,holder.category);
            }else {
                showCategory(true,holder.category);
            }
        }
        holder.category.setText(result.getType());
        holder.content.setText(result.getDesc());
    }

    private void showCategory(boolean show, TextView tvCategory) {
        if (show) {
            tvCategory.setVisibility(View.VISIBLE);
        } else {
            tvCategory.setVisibility(View.GONE);
        }
    }


    static class TodayViewHolder extends RecyclerView.ViewHolder {
        TextView category;
        TextView content;

        TodayViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.tv_category);
            content = itemView.findViewById(R.id.tv_gank_desc);
        }
    }
}
