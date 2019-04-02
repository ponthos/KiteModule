package com.jiayuan.jr.kitemodule.app.ui.Activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
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
    internal var MeasuredWidth: Int = 0
    internal var MeasuredHeight: Int = 0
    override fun setupActivityComponent(appComponent: AppComponent) {

    }

    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.kitemodulemodule_activity_bessel
    }

    override fun initData(savedInstanceState: Bundle?) {
        //        bg_image=findViewById(R.id.bg_image);
//        advance = findViewById(R.id.advance)
//        fc = findViewById(R.id.folding_cell)
//        scrollingBackground = findViewById(R.id.scrolling_background)
//        end = findViewById(R.id.end)
        scrolling_background.stop()
        // get our folding cell

        // get our folding cell
        //        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        // set custom parameters
        //方法弃用修改为图片
        //        fc.initialize(1000, Color.WHITE, 3);
        //        fc.initialize(30, 1000, Color.DKGRAY, 2);
        // or with camera height parameter


        // attach click listener to folding cell
        //        fc.setOnClickListener(new View.OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                fc.toggle(false);
        //            }
        //        });
        //        ObjectAnimator animator = ObjectAnimator.ofFloat(fc, "scaleX", 1f, 3f, 1f);
        //        animator.setDuration(5000);
        //        animator.start();
        imageView = ImageView(this)
        imageView.setImageDrawable(resources.getDrawable(R.drawable.envelope_face))
        end.setOnClickListener {
            folding_cell.toggle(false, folding_cell, scrolling_background, advance)
            //                advance.addHeart();
        }

        advance.setOnClickListener {
            //                scrollingBackground.stop();
            //                scrollingBackground.start();
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