package com.pigrange.rxjavaretrofittest.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.pigrange.rxjavaretrofittest.Adapter.TodayGankAdapter;
import com.pigrange.rxjavaretrofittest.BaseClass.BaseAdapter;
import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.R;
import com.pigrange.rxjavaretrofittest.Utils.RxJavaUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TodayGankFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ImageView mImageView;
    private TodayGankAdapter adapter;

    private final List<GanHuo.Result> mResultList = new ArrayList<>();

    public static TodayGankFragment getIntance() {
        return new TodayGankFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_today, container, false);
        mRecyclerView = view.findViewById(R.id.today_gank);
        mImageView = view.findViewById(R.id.today_image);
        initToday();
        return view;
    }

    private void initToday() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());

        adapter = new TodayGankAdapter(mResultList);
        adapter.setAdapterItemClickListener(new BaseAdapter.AdapterItemClickListener() {
            @Override
            public void click(Intent intent) {
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(manager);
        getToday();
    }

    private void getToday() {
        RxJavaUtil.getToday().subscribe(new Observer<GanHuo>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("onSubscribe", "succeed getToday");
            }

            @Override
            public void onNext(GanHuo value) {
                mResultList.clear();
                mResultList.addAll(value.getResults());
                for (GanHuo.Result result:mResultList){
                    if (result.getType().equals("福利")){
                        loadImage(result.getUrl());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e("GetToday","加载失败");
            }

            @Override
            public void onComplete() {
                Log.e("GetToday","加载完成");
            }
        });
    }

    private void loadImage(String url){
        Glide.with(getContext()).load(url).into(mImageView);
    }

}
