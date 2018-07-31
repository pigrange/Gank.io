package com.pigrange.Gank.Interface;

import com.pigrange.Gank.Model.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetRandomImageService {
    @GET("api/random/data/{type}/{count}")
    Observable<GanHuo> getRandomImage(@Path("type")String type, @Path("count")int count);

}
