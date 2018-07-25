package com.pigrange.rxjavaretrofittest.Retrofit;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;
import com.pigrange.rxjavaretrofittest.Model.ZhiHuDaily;
import com.pigrange.rxjavaretrofittest.Model.ZhiHuNews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page")int page);

    @GET("api/{type}")
    Observable<GanHuo> getToday(@Path("type")String type);

    @GET("api/random/data/{type}/{count}")
    Observable<GanHuo> getRandomImage(@Path("type")String type,@Path("count")int count);

    @GET("api/4/news/before/{id}")
    Observable<ZhiHuDaily> getZhihuDaily(@Path("id")int id);

    @GET("api/4/news/{id}")
    Observable<ZhiHuNews> getZhihuNews(@Path("id")String id);
}
