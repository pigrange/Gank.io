package com.pigrange.Gank.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pigrange.Gank.Activity.WebViewActivity;
import com.pigrange.Gank.BaseClass.BaseAdapter;
import com.pigrange.Gank.Model.BaseResult;
import com.pigrange.Gank.Model.GanHuo;
import com.pigrange.Gank.R;

import java.util.List;

public class TodayGankAdapter extends BaseAdapter<TodayGankAdapter.TodayViewHolder> {
    private List<BaseResult> mResultList;
    private Context mContext;
    private AdapterOnclickListener mAdapterItemClickListener;

    public TodayGankAdapter(List<BaseResult> results) {
        super(results);
        this.mResultList = results;
    }

    @Override
    public BaseHolder onChildCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.fragment_show_today, parent, false);
        final TodayViewHolder holder = new TodayViewHolder(v);
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = (GanHuo.Result) mResultList.get(position);
                Intent intent = new Intent(mContext, WebViewActivity.class);
                intent.putExtra("url", result.getUrl());
                mAdapterItemClickListener.onClick(intent);
            }
        });
        return holder;
    }

    @Override
    public void onChildBindViewHolder(@NonNull TodayViewHolder holder, int position) {
        GanHuo.Result result = (GanHuo.Result) mResultList.get(position);
        GanHuo.Result result1 = (GanHuo.Result)mResultList.get(position-1);
        if (position == 0){
            showCategory(true,holder.category);
        }else {
            if (result.getType().equals(result1.getType())){
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

    public void setAdapterItemClickListener(AdapterOnclickListener adapterItemClickListener) {
        mAdapterItemClickListener = adapterItemClickListener;
    }


    static class TodayViewHolder extends BaseAdapter.BaseHolder {
        TextView category;
        TextView content;

        TodayViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.tv_category);
            content = itemView.findViewById(R.id.tv_gank_desc);
        }
    }
}
