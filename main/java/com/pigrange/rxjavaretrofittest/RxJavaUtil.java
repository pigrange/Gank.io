package com.pigrange.rxjavaretrofittest;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.Retrofit.MyRetrofit;
import com.pigrange.rxjavaretrofittest.Retrofit.MyService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil {
    public static Observable<GanHuo> getObservable(String type, int count) {
        return MyRetrofit
                .getRetrofit()
                .create(MyService.class)
                .getGanhuo(type, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
