package com.pigrange.Gank.Utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static final String GANKIO_URL = "http://gank.io";
    private static final String ZHIHU_URL = "http://news-at.zhihu.com";

    public static Retrofit getGankRetrofit() {
        return RetrofitHolder.mGanRetrofit;
    }

    public static Retrofit getZhiHuRetrofit(){
        return RetrofitHolder.mZhiHuRetrofit;
    }

    private static class RetrofitHolder {
        private static Retrofit mGanRetrofit = new Retrofit
                .Builder()
                .baseUrl(GANKIO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        private static Retrofit mZhiHuRetrofit = new Retrofit
                .Builder()
                .baseUrl(ZHIHU_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        private RetrofitHolder() {
        }
    }
}
