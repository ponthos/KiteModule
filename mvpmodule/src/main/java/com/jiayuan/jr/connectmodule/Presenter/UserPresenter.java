package com.jiayuan.jr.connectmodule.Presenter;

import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BasePresenter;
import com.jiayuan.jr.connectmodule.Contract.UserContract;

import javax.inject.Inject;


@ActivityScope
public class UserPresenter extends BasePresenter<UserContract.Model, UserContract.View> {

    @Inject
    public UserPresenter(UserContract.Model model, UserContract.View rootView) {
        super(model, rootView);
    }
    //这里定义业务方法, 响应用户的交互
    public void requestUsers(final boolean pullToRefresh) {
    }

}