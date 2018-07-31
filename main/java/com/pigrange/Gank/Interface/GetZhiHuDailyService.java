package com.pigrange.Gank.Interface;

import com.pigrange.Gank.Model.ZhiHuDaily;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetZhiHuDailyService {
    @GET("api/4/news/before/{id}")
    Observable<ZhiHuDaily> getZhihuDaily(@Path("id")int id);
}
