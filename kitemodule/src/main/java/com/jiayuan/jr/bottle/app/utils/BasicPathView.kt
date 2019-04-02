package com.jiayuan.jr.kitemodule.app.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout

class BasicPathView : ConstraintLayout {

    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var startX: Float = 0.toFloat()
    private var startY: Float = 0.toFloat()
    private var endX: Float = 0.toFloat()
    private var endY: Float = 0.toFloat()
    private var touchX: Float = 0.toFloat()
    private var touchY: Float = 0.toFloat()

    private var isFill: Boolean = false

    private var index: Int = 0

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView()
    }


    private fun initView() {
        mPaint = Paint()
        mPaint!!.isAntiAlias = true
        mPath = Path()

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        startX = (measuredWidth / 5).toFloat()
        endY = (measuredHeight / 2).toFloat()
        startY = endY
        endX = (measuredWidth * 4 / 5).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint!!.strokeWidth = 10f
        mPaint!!.color = Color.RED
        mPaint!!.style = Paint.Style.FILL
        canvas.drawCircle(startX, startY, 10f, mPaint!!)
        canvas.drawCircle(endX, endY, 10f, mPaint!!)
        canvas.drawCircle(touchX, touchY, 10f, mPaint!!)
        mPaint!!.textSize = 20f
        canvas.drawText("这是一阶贝塞尔曲线,就一条直线,没什么好说的", startX, startY / 4, mPaint!!)

        mPaint!!.color = Color.BLUE
        if (isFill) {
            mPaint!!.style = Paint.Style.FILL
        } else {
            mPaint!!.style = Paint.Style.STROKE
        }
        //绘制一阶
        mPath!!.moveTo(startX, startY / 3)
        mPath!!.lineTo(endX, endY / 3)
        canvas.drawPath(mPath!!, mPaint!!)
        mPath!!.reset()


        mPath!!.moveTo(startX, startY)
        mPath!!.quadTo(touchX, touchY, endX, endY)
        canvas.drawPath(mPath!!, mPaint!!)
        mPath!!.reset()


    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        touchX = event.x
        touchY = event.y
        postInvalidate()
        return true
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        val button = Button(context)
        button.text = "填充"

        button.setOnClickListener {
            if (index % 2 == 0) {
                isFill = true
                button.text = "不填充"
            } else {
                isFill = false
                button.text = "填充"

            }
            index++
        }

        addView(button)
    }
}
