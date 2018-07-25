package com.pigrange.rxjavaretrofittest.Utils;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.Retrofit.MyService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil {
    public static Observable<GanHuo> getGanHuo(String type, int count, int page) {
        return MyRetrofit
                .getGankRetrofit()
                .create(MyService.class)
                .getGanHuo(type, count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<GanHuo> getToday(){
        return MyRetrofit
                .getGankRetrofit()
                .create(MyService.class)
                .getToday("today")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<GanHuo> getRandomImage(){
        return MyRetrofit.getGankRetrofit()
                .create(MyService.class)
                .getRandomImage("福利",20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
