package com.pigrange.Gank.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pigrange.Gank.Adapter.GanHuoAdapter;
import com.pigrange.Gank.BaseClass.BaseAdapter;
import com.pigrange.Gank.BaseClass.LoadMoreScrollListener;
import com.pigrange.Gank.Model.BaseResult;
import com.pigrange.Gank.Model.GanHuo;
import com.pigrange.Gank.Adapter.MeiZhiAdapter;
import com.pigrange.Gank.R;
import com.pigrange.Gank.Utils.RxJavaUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class GanHuoFragment extends Fragment {
    private String title;
    private RecyclerView mRecyclerView;
    private BaseAdapter adapter;
    private LinearLayoutManager manager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LoadMoreScrollListener loadMoreScrollListener;
    private Observer<GanHuo> mGanHuoObserver;

    final List<BaseResult> mResultList = new ArrayList<>();

    public static GanHuoFragment getInstance(String title) {
        GanHuoFragment ganHuoFragment = new GanHuoFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        ganHuoFragment.setArguments(bundle);
        return ganHuoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        mRecyclerView = v.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = v.findViewById(R.id.mSwipeRefreshLayout);
        initObserver();
        initRecyclerView();
        return v;
    }

    private void initObserver() {
        mGanHuoObserver = new Observer<GanHuo>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "succeed");
            }

            @Override
            public void onNext(GanHuo value) {
                if (mResultList.size() == 0) {
                    mResultList.clear();
                    mResultList.addAll(value.getResults());
                    adapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else if (mSwipeRefreshLayout.isRefreshing()) {
                    mResultList.clear();
                    mResultList.addAll(value.getResults());
                    adapter.notifyDataSetChanged();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else {
                    mResultList.addAll(value.getResults());
                    adapter.notifyItemChanged(mResultList.size() + 1);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("GanHuoFragment", "ganhuo加载失败");
                e.printStackTrace();
                Snackbar.make(mRecyclerView, "网络似乎出现了一点异常呀......", Snackbar.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                Log.i("onComplete", "加载完成");
            }
        };
    }

    private void initRecyclerView() {
        if (title.equals("福利")) {
            manager = new GridLayoutManager(getContext(), 2);
            adapter = new MeiZhiAdapter(mResultList);
        } else {
            manager = new LinearLayoutManager(getContext());
            adapter = new GanHuoAdapter(mResultList);

        }
        mRecyclerView.setLayoutManager(manager);
        View footer = LayoutInflater.from(getContext()).inflate(R.layout.footer_layout,mRecyclerView,false);
        adapter.setFooter(footer);
        adapter.setOnclickListener(new BaseAdapter.AdapterOnclickListener() {
            @Override
            public void onClick(Intent intent) {
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);

        loadMoreScrollListener = new LoadMoreScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadData(currentPage + 1);
            }
        };
        mRecyclerView.addOnScrollListener(loadMoreScrollListener);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                reLoad();
            }
        });
        loadData(1);
    }

    private void loadData(int page) {
        if (title.equals("福利")) {
            getRandomImage();
        } else {
            getData(title, 20, page);
        }
    }

    private void reLoad() {
        loadMoreScrollListener.refreshTotal();
        loadData(1);
    }

    private void getData(String type, int count, int page) {
        RxJavaUtil.getGanHuo(type, count, page).subscribe(mGanHuoObserver);
    }

    private void getRandomImage() {
        RxJavaUtil.getRandomImage().subscribe(mGanHuoObserver);
    }

    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

}
