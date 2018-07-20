package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pigrange.rxjavaretrofittest.Activity.ShowGanHuo;
import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.R;

import java.util.List;

public class GanHuoAdapter extends BaseAdapter<GanHuoAdapter.GanHuoHolder> {
    private Context mContext;
    private List<GanHuo.Result> mResultList;

    public GanHuoAdapter(List<GanHuo.Result> list) {
        super(list);
        mResultList = list;
    }

    @NonNull
    @Override
    public GanHuoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.ganhuo_item, parent, false);
        final GanHuoHolder holder = new GanHuoHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = mResultList.get(position);
                Intent intent = new Intent(mContext, ShowGanHuo.class);
                intent.putExtra("url",result.getUrl());
                onItemClick(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GanHuoHolder holder, int position) {
        GanHuo.Result result = mResultList.get(position);
        holder.content.setText(result.getDesc());
        holder.tag.setBackgroundResource(R.drawable.tag_android);
        holder.tag.setText(result.getType());
    }

    static class GanHuoHolder extends RecyclerView.ViewHolder {
        TextView content;
        TextView tag;

        GanHuoHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            tag = itemView.findViewById(R.id.contentTag);
        }
    }

}
