package com.jiayuan.jr.kitemodule.app.ui.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.alibaba.android.arouter.facade.annotation.Route
import com.jiayuan.jr.kitemodule.R

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = "/test/activity")
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
