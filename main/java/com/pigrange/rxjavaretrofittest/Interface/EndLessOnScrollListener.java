package com.pigrange.rxjavaretrofittest.Interface;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndLessOnScrollListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager mLinearLayoutManager;
    private int currentPage = 0;
    private int previousTotal = 0;
    private boolean loading = true;

    public EndLessOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleItemCount = recyclerView.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem) {
            currentPage++;
            onLoadMore(currentPage);
            loading = true;
        }
    }

    public void refreshTotal() {
        previousTotal = 0;
        currentPage = 0;
        loading = true;
    }

    public abstract void onLoadMore(int currentPage);
}
