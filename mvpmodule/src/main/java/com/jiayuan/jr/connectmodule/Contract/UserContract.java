package com.jiayuan.jr.connectmodule.Contract;

import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.IModel;
import com.jess.arms.mvp.IView;
import com.jiayuan.jr.modelmodule.ResponseModel.UserResponse;

import java.util.List;

import io.reactivex.Observable;

public interface UserContract {
    //对于经常在日常开发中使用到的关于 UI 的方法可以定义到 IView 中, 如显示隐藏进度条, 和显示文字消息
    interface View extends IView {
        void setAdapter(DefaultAdapter adapter);
        void startLoadMore();
        void endLoadMore();
    }
    //Model 层定义接口, 外部只需关心 Model 返回的数据, 无需关心内部细节, 即是否使用缓存
    interface Model extends IModel {
        Observable<List<UserResponse>> getUsers(int lastIdQueried, boolean update);
    }
}