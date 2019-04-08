package com.jiayuan.jr.connectmodule.Service;

import com.jiayuan.jr.modelmodule.ResponseModel.ArticResponse;
import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public  interface KiteService {
    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    //        @Headers(value = {HEADER_API_VERSION})
    @GET("/users")
    public Observable<List<UserResponse>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);

    @POST("/artic/getartic")
    public Observable<List<ArticResponse>> getArticle(@Query("since") int userid);

}
