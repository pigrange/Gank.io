package com.pigrange.Gank.BaseClass;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.pigrange.Gank.Model.BaseResult;

import java.util.List;

public abstract class BaseAdapter<T extends BaseAdapter.BaseHolder> extends RecyclerView.Adapter<BaseAdapter.BaseHolder> {
    private View footer = null;
    private final int TYPE_FOOTER_TYPE = 0x01001;
    private boolean isHasFooter;
    private List<BaseResult> mResultList;
    private AdapterOnclickListener mOnclickListener;

    public BaseAdapter(List<BaseResult> results){
        this.mResultList = results;
    }

    public void setOnclickListener(AdapterOnclickListener onclickListener) {
        mOnclickListener = onclickListener;
    }

    protected void OnItemClick(Intent intent) {
        mOnclickListener.onClick(intent);
    }

    public interface AdapterOnclickListener {
        void onClick(Intent intent);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mResultList.size()) {
            return TYPE_FOOTER_TYPE;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER_TYPE) {
            return new BaseHolder(footer);
        } else {
            return onChildCreateViewHolder(parent, viewType);
        }
    }

    public abstract BaseHolder onChildCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
        if (position == mResultList.size()) {
            return;
        } else {
            onChildBindViewHolder((T) holder, position);
        }
    }

    public abstract void onChildBindViewHolder(@NonNull T holder, int position);

    @Override
    public int getItemCount() {
        if (isHasFooter) {
            return mResultList.size() + 1;
        }
        return mResultList.size();
    }

    public View getFooter() {
        return footer;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        final RecyclerView.LayoutManager  layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int viewType = getItemViewType(position);
                    if (viewType == TYPE_FOOTER_TYPE){
                        return ((GridLayoutManager) layoutManager).getSpanCount();
                    }
                    return 1;
                }
            });
        }
    }

    public void setFooter(View footer) {
        isHasFooter = true;
        notifyDataSetChanged();
        this.footer = footer;
    }

    public static class BaseHolder extends RecyclerView.ViewHolder {
        public BaseHolder(View itemView) {
            super(itemView);
        }
    }
}
