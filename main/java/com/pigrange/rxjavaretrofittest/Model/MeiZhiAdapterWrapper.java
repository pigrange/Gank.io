package com.pigrange.rxjavaretrofittest.Model;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MeiZhiAdapterWrapper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    enum ITEM_TYPE {
        HEADER,
        FOOTER,
        NORMAL
    }

    private MeiZhiAdapter mMeiZhiAdapter;
    private View mHeaderView;
    private View mFooterView;

    public MeiZhiAdapterWrapper(MeiZhiAdapter adapter) {
        mMeiZhiAdapter = adapter;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE.HEADER.ordinal();
        } else if (position == mMeiZhiAdapter.getItemCount() + 1) {
            return ITEM_TYPE.FOOTER.ordinal();
        } else {
            return ITEM_TYPE.NORMAL.ordinal();
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.HEADER.ordinal()) {
            return new MeiZhiAdapter.ViewHolder(mHeaderView);
        } else if (viewType == ITEM_TYPE.FOOTER.ordinal()) {
            return new MeiZhiAdapter.ViewHolder(mFooterView);
        } else {
            return mMeiZhiAdapter.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            return;
        } else if (position == mMeiZhiAdapter.getItemCount() + 1) {
            return;
        } else {
            mMeiZhiAdapter.onBindViewHolder((MeiZhiAdapter.ViewHolder) holder, position - 1);
        }
    }

    @Override
    public int getItemCount() {
        return mMeiZhiAdapter.getItemCount() + 2;
    }

    public void addHeaderView(View v) {
        this.mHeaderView = v;
    }

    public void addFooterView(View v) {
        this.mFooterView = v;
    }
}
