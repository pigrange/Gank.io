package com.pigrange.rxjavaretrofittest.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;

import java.util.List;

public abstract class BaseAdapter<T extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<T> {
    private List<GanHuo.Result> mResultList;
    void onItemClick(Intent intent) {
        mClickListener.click(intent);
    }

    private AdapterItemClickListener mClickListener;

    BaseAdapter(List<GanHuo.Result> results) {
        mResultList = results;
    }

    public void setAdapterItemClickListener(AdapterItemClickListener listener) {
        mClickListener = listener;
    }

    public interface AdapterItemClickListener {
        void click(Intent intent);
    }

    @Override
    public int getItemCount() {
        return mResultList.size();
    }

}
