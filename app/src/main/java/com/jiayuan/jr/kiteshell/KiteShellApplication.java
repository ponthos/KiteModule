package com.jiayuan.jr.kiteshell;

import com.jess.arms.base.BaseApplication;

public class KiteShellApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }
}
