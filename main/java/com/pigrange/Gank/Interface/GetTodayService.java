package com.pigrange.Gank.Interface;

import com.pigrange.Gank.Model.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetTodayService {
    @GET("api/{type}")
    Observable<GanHuo> getToday(@Path("type")String type);

}
