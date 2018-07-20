package com.pigrange.rxjavaretrofittest.Retrofit;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page")int page);

    @GET("api/data/today)")
    Observable<GanHuo> getToday();

    @GET("api/random/data/{type}/{count}")
    Observable<GanHuo> getRandomImage(@Path("type")String type,@Path("count")int count);
}
