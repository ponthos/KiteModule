package com.jiayuan.jr.connectmodule.Module;

import com.jess.arms.di.scope.ActivityScope;
import com.jiayuan.jr.connectmodule.Contract.UserContract;
import com.jiayuan.jr.connectmodule.Model.UserModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    private UserContract.View view;

    //构建UserModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserContract.View provideUserView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    UserContract.Model provideUserModel(UserModel model){
        return model;
    }
}
