package com.jiayuan.jr.connectmodule.Model;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseModel;
import com.jiayuan.jr.connectmodule.Contract.UserContract;
import com.jiayuan.jr.connectmodule.Service.KiteService;
import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class UserModel extends BaseModel implements UserContract.Model{

    @Inject
    public UserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<List<UserResponse>> getUsers(int lastIdQueried, boolean update) {
        return (Observable<List<UserResponse>>) mRepositoryManager.obtainRetrofitService(KiteService.class)
                .getUsers(1,0);
    }
}