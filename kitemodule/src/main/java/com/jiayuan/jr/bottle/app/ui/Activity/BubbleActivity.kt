package com.jiayuan.jr.kitemodule.app.ui.Activity

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jiayuan.jr.basemodule.HearView
import com.jiayuan.jr.bottle.app.ui.Activity.BaseActivity
import com.jiayuan.jr.kitemodule.R
import java.io.InputStream

@Suppress("DEPRECATION")
@Route(path = "/kite_module/bubble_activity")
class BubbleActivity() : BaseActivity<IPresenter>() {

    override fun setupActivityComponent(appComponent: AppComponent) {

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.kitemodulemodule_activity_bubble
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        val layout = findViewById<HearView>(R.id.read_bubble_view)
        val `is`: InputStream = resources.openRawResource(R.drawable.bg_pop)
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888
        opt.inPurgeable = true
        opt.inInputShareable = true
        opt.inSampleSize = 2
        val bm = BitmapFactory.decodeStream(`is`, null, opt)
        val bd = BitmapDrawable(resources, bm)
        layout.setBackgroundDrawable(bd)
    }

}
