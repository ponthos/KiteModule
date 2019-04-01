package com.jiayuan.jr.connectmodule;

import android.database.Observable;

import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<UserResponse>>> getUsers(Observable<List<UserResponse>> oUsers, DynamicKey idLastUserQueried, EvictProvider evictProvider);

}
