package com.pigrange.rxjavaretrofittest.Fragment;

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

import com.pigrange.rxjavaretrofittest.Interface.EndLessOnScrollListener;
import com.pigrange.rxjavaretrofittest.Adapter.BaseAdapter;
import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.Adapter.GanHuoAdapter;
import com.pigrange.rxjavaretrofittest.Adapter.MeiZhiAdapter;
import com.pigrange.rxjavaretrofittest.R;
import com.pigrange.rxjavaretrofittest.RxJava.RxJavaUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainFragment extends Fragment {
    private String title;
    private BaseAdapter adapter;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager manager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EndLessOnScrollListener mEndLessOnScrollListener;

    final List<GanHuo.Result> mResultList = new ArrayList<>();

    public static MainFragment getFragment(String title) {
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        mainFragment.setArguments(bundle);
        return mainFragment;
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
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        mRecyclerView = v.findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout = v.findViewById(R.id.mSwipeRefreshLayout);
        init();
        return v;
    }

    private void init() {
        mResultList.clear();
        if (title.equals("福利")||title.equals("random")) {
            manager = new GridLayoutManager(getContext(), 2);
            adapter = new MeiZhiAdapter(mResultList);
        } else {
            manager = new LinearLayoutManager(getContext());
            adapter = new GanHuoAdapter(mResultList);
        }

        adapter.setAdapterItemClickListener(new BaseAdapter.AdapterItemClickListener() {
            @Override
            public void click(Intent intent) {
                startActivity(intent);
            }
        });

        mRecyclerView.setLayoutManager(manager);
        mRecyclerView.setAdapter(adapter);

        mEndLessOnScrollListener = new EndLessOnScrollListener(manager) {
            @Override
            public void onLoadMore(int currentPage) {
                loadData(currentPage + 1);
            }
        };
        mRecyclerView.addOnScrollListener(mEndLessOnScrollListener);
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
            getData("福利", 20, page);
        } else if (title.equals("random")) {
            getRandomImage();
        } else {
            if (title.equals("Android")) {
                getData(title, 20, page);
            } else if (title.equals("iOS")) {
                getData("iOS", 20, page);
            }
        }
    }

    private void reLoad() {
        mResultList.clear();
        mEndLessOnScrollListener.refreshTotal();
        loadData(1);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void getData(String type, int count, int page) {
        RxJavaUtil.getGanHuo(type, count, page).subscribe(new Observer<GanHuo>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "succeed");
            }

            @Override
            public void onNext(GanHuo value) {
                if (mResultList.size() == 0) {
                    mResultList.addAll(value.getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    mResultList.addAll(value.getResults());
                    adapter.notifyItemInserted(mResultList.size() + 1);
                }
            }

            @Override
            public void onError(Throwable e) {
                Snackbar.make(mRecyclerView, "NO WIFI，不能愉快的看妹纸啦..", Snackbar.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                Log.i("onComplete", "加载完成");
            }
        });
    }

    private void getRandomImage() {
        RxJavaUtil.getRandomImage().subscribe(new Observer<GanHuo>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "succeed");
            }

            @Override
            public void onNext(GanHuo value) {
                if (mResultList.size() == 0) {
                    mResultList.addAll(value.getResults());
                    adapter.notifyDataSetChanged();
                } else {
                    mResultList.addAll(value.getResults());
                    adapter.notifyItemInserted(mResultList.size() + 1);
                }
            }

            @Override
            public void onError(Throwable e) {
                Log.e("onError","随机加载图片失败");
                Snackbar.make(mRecyclerView, "NO WIFI，不能愉快的看妹纸啦..", Snackbar.LENGTH_LONG).show();
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onComplete() {
                Log.i("onComplete", "加载完成");
            }
        });
    }

    private void getToday() {
        RxJavaUtil.getToday().subscribe(new Observer<GanHuo>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "succeed getToday");
            }

            @Override
            public void onNext(GanHuo value) {
                //getToday
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

}
