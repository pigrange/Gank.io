package com.pigrange.Gank.Interface;

import com.pigrange.Gank.Model.GanHuo;
import com.pigrange.Gank.Model.ZhiHuDaily;
import com.pigrange.Gank.Model.ZhiHuNews;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GetZhihuNewsService {
    @GET("api/4/news/{id}")
    Observable<ZhiHuNews> getZhihuNews(@Path("id")String id);
}
