package com.jiayuan.jr.kitemodule.app.ui.Activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jiayuan.jr.bottle.app.ui.Activity.BaseActivity
import com.jiayuan.jr.kitemodule.R
import kotlinx.android.synthetic.main.kitemodulemodule_activity_bessel.*
import java.util.*


@Suppress("DEPRECATION")
@Route(path = "/kite_module/bessel_activity")
class BesselActivity : BaseActivity<IPresenter>() {
    internal var bg_image: ImageView? = null
    private var MeasuredWidth: Int = 0
    private var MeasuredHeight: Int = 0
    private var  bitmap: Bitmap? = null
    override fun setupActivityComponent(appComponent: AppComponent) {

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.kitemodulemodule_activity_bessel
    }

    override fun initData(savedInstanceState: Bundle?) {
        scrolling_background.stop()
        val imageView_timg = ImageView(this)
        val params = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.addRule(RelativeLayout.ALIGN_PARENT_END)
        //设置位置显示
        params.setMargins(0,0,120,195)
//        imageView.setImageBitmap(drawHeart(colors[random.nextInt(colors.length)]));
        bitmap = BitmapFactory.decodeResource(resources, com.jiayuan.jr.basemodule.R.drawable.timg)
        imageView_timg.setImageBitmap(bitmap)
        advance.addView(imageView_timg, params)
        imageView = ImageView(this)
        imageView.setImageDrawable(resources.getDrawable(com.jiayuan.jr.kitemodule.R.drawable.envelope_face))
        end.setOnClickListener {
            folding_cell.toggle(false, folding_cell, scrolling_background, advance,imageView_timg)
        }
        advance.setOnClickListener {
            if (folding_cell.visibility != View.GONE) {
                folding_cell.toggle(false)
            }
        }
    }
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val view = window.decorView
            MeasuredWidth = view.measuredWidth
            MeasuredHeight = view.measuredHeight
            val drawables = ArrayList<Drawable>()
            drawables.add(resources.getDrawable(R.drawable.envelope2))
            drawables.add(resources.getDrawable(R.drawable.envelope3))
            folding_cell.initialize(1000, drawables, 2)
        }
    }

    companion object {
        //    @Override
        //    protected void onCreate(Bundle savedInstanceState) {
        //        super.onCreate(savedInstanceState);
        //        setContentView(R.layout.kitemodulemodule_activity_bessel);
        //    }
//        lateinit var advance: AdvancePathView
//        lateinit var scrollingBackground: ScrollingImageView
//        lateinit var end: TextView
        lateinit var imageView: ImageView
//        lateinit var fc: FoldingCell
    }
}