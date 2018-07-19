package com.pigrange.rxjavaretrofittest.Retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofit {
    private static String GANKIO_URL = "http://gank.io";

    public static Retrofit getRetrofit() {
        return RetrofitHolder.mRetrofit;
    }

    private static class RetrofitHolder {
        private static Retrofit mRetrofit = new Retrofit
                .Builder()
                .baseUrl(GANKIO_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        private RetrofitHolder() {
        }
    }
}
