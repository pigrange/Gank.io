package com.pigrange.Gank.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pigrange.Gank.BaseClass.BaseAdapter;
import com.pigrange.Gank.Model.BaseResult;
import com.pigrange.Gank.Model.GanHuo;
import com.pigrange.Gank.R;
import com.pigrange.Gank.Utils.JumpUtil;
import com.pigrange.Gank.Utils.TimeUtil;

import java.util.List;

public class GanHuoAdapter extends BaseAdapter<GanHuoAdapter.GanHuoHolder> {
    private Context mContext;
    private List<BaseResult> mResultList;

    public GanHuoAdapter(List<BaseResult> results){
        super(results);
        this.mResultList = results;
    }

    @Override
    public BaseHolder onChildCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View v = LayoutInflater.from(mContext).inflate(R.layout.ganhuo_item, parent, false);
        final GanHuoHolder holder = new GanHuoHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                GanHuo.Result result = (GanHuo.Result) mResultList.get(position);
                JumpUtil.startWebView(mContext.getApplicationContext(), JumpUtil.SHOW_GANHUO,result.getUrl(),result.getDesc());
            }
        });
        return holder;
    }

    @Override
    public void onChildBindViewHolder(@NonNull GanHuoHolder holder, int position) {
        GanHuo.Result result = (GanHuo.Result) mResultList.get(position);
        holder.content.setText(result.getDesc());
        holder.tag.setText(result.getType());
        holder.provider.setText("@" + result.getWho());
        holder.time.setText(TimeUtil.parseTime(result.getPublishedAt()));
        setTagColor(result.getType(), holder);
    }

    private void setTagColor(String type, GanHuoHolder holder) {
        if (type.equals("Android")) {
            holder.tag.setBackgroundResource(R.drawable.tag_android);
        } else if (type.equals("iOS")) {
            holder.tag.setBackgroundResource(R.drawable.tag_ios);
        } else if (type.equals("前端")) {
            holder.tag.setBackgroundResource(R.drawable.tag_qianduan);
        } else if (type.equals("App")) {
            holder.tag.setBackgroundResource(R.drawable.tag_app);
        }
    }

     static class GanHuoHolder extends BaseAdapter.BaseHolder {
        TextView content;
        TextView tag;
        TextView provider;
        TextView time;

        GanHuoHolder(View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            tag = itemView.findViewById(R.id.contentTag);
            provider = itemView.findViewById(R.id.contentProvider);
            time = itemView.findViewById(R.id.provideTime);
        }
    }

}
