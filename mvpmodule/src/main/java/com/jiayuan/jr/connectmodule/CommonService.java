package com.jiayuan.jr.connectmodule;


import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface CommonService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<UserResponse>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);
}