package com.pigrange.Gank.Utils;

import com.pigrange.Gank.Interface.GetGanHuoService;
import com.pigrange.Gank.Interface.GetRandomImageService;
import com.pigrange.Gank.Interface.GetTodayService;
import com.pigrange.Gank.Interface.GetZhihuNewsService;
import com.pigrange.Gank.Model.GanHuo;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxJavaUtil {
    public static Observable<GanHuo> getGanHuo(String type, int count, int page) {
        return MyRetrofit
                .getGankRetrofit()
                .create(GetGanHuoService.class)
                .getGanHuo(type, count,page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<GanHuo> getToday(){
        return MyRetrofit
                .getGankRetrofit()
                .create(GetTodayService.class)
                .getToday("today")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
    public static Observable<GanHuo> getRandomImage(){
        return MyRetrofit.getGankRetrofit()
                .create(GetRandomImageService.class)
                .getRandomImage("福利",20)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
