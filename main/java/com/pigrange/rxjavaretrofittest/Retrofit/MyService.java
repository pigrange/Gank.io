package com.pigrange.rxjavaretrofittest.Retrofit;

import com.pigrange.rxjavaretrofittest.Model.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyService {
    @GET("api/random/data/{type}/{count}")
    Observable<GanHuo> getGanhuo(@Path("type") String type, @Path("count") int count);
}
