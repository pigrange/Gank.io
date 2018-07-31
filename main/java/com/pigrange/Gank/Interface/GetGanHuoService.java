package com.pigrange.Gank.Interface;

import com.pigrange.Gank.Model.GanHuo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetGanHuoService {
    @GET("api/data/{type}/{count}/{page}")
    Observable<GanHuo> getGanHuo(@Path("type") String type, @Path("count") int count, @Path("page")int page);
}
