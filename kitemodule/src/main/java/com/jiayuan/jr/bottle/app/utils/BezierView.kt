package com.jiayuan.jr.kitemodule.app.utils

import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Message
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.text.DecimalFormat
import java.util.*

/**
 * 贝塞尔曲线
 *
 * @author venshine
 */
class BezierView : View {

    private var mBezierPath: Path? = null    // 贝塞尔曲线路径

    private var mBezierPaint: Paint? = null  // 贝塞尔曲线画笔
    private var mMovingPaint: Paint? = null  // 移动点画笔
    private var mControlPaint: Paint? = null  // 控制点画笔
    private var mTangentPaint: Paint? = null  // 切线画笔
    private var mLinePaint: Paint? = null    // 固定线画笔
    private var mTextPointPaint: Paint? = null    // 点画笔
    private var mTextPaint: Paint? = null    // 文字画笔

    private var mBezierPoints: ArrayList<PointF>? = null // 贝塞尔曲线点集
    private var mBezierPoint: PointF? = null // 贝塞尔曲线移动点

    private var mControlPoints: ArrayList<PointF>? = null    // 控制点集

    private var mTangentPoints: ArrayList<ArrayList<ArrayList<PointF>>>? = null // 切线点集

    private var mInstantTangentPoints: ArrayList<ArrayList<PointF>>? = null

    private var mR = 0  // 移动速率

    private var mRate = RATE   // 速率

    private var mState: Int = 0 // 状态

    private var mLoop = false  // 设置是否循环

    private var mTangent = true   // 设置是否显示切线

    private var mWidth = 0
    private var mHeight = 0    // 画布宽高

    private var mCurPoint: PointF? = null // 当前移动的控制点

    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == HANDLER_WHAT) {
                mR += mRate
                if (mR >= mBezierPoints!!.size) {
                    removeMessages(HANDLER_WHAT)
                    mR = 0
                    mState = mState and STATE_RUNNING.inv()
                    mState = mState and STATE_STOP.inv()
                    mState = mState or (STATE_READY or STATE_TOUCH)
                    if (mLoop) {
                        start()
                    }
                    return
                }
                if (mR != mBezierPoints!!.size - 1 && mR + mRate >= mBezierPoints!!.size) {
                    mR = mBezierPoints!!.size - 1
                }
                // Bezier点
                mBezierPoint = PointF(mBezierPoints!![mR].x, mBezierPoints!![mR].y)
                // 切线点
                if (mTangent) {
                    val size = mTangentPoints!!.size
                    var instantpoints: ArrayList<PointF>
                    mInstantTangentPoints = ArrayList()
                    for (i in 0 until size) {
                        val len = mTangentPoints!![i].size
                        instantpoints = ArrayList()
                        for (j in 0 until len) {
                            val x = mTangentPoints!![i][j][mR].x
                            val y = mTangentPoints!![i][j][mR].y
                            instantpoints.add(PointF(x, y))
                        }
                        mInstantTangentPoints!!.add(instantpoints)
                    }
                }
                if (mR == mBezierPoints!!.size - 1) {
                    mState = mState or STATE_STOP
                }
                invalidate()
            }
        }
    }

    private val isReady: Boolean
        get() = mState and STATE_READY == STATE_READY

    private val isRunning: Boolean
        get() = mState and STATE_RUNNING == STATE_RUNNING

    private val isTouchable: Boolean
        get() = mState and STATE_TOUCH == STATE_TOUCH

    private val isStop: Boolean
        get() = mState and STATE_STOP == STATE_STOP

    /**
     * 贝塞尔曲线阶数
     *
     * @return
     */
    /**
     * 设置贝塞尔曲线阶数
     *
     * @param order
     */
    var order: Int
        get() = mControlPoints!!.size - 1
        set(order) {
            if (order == order) {
                return
            }
            stop()
            val size = order - order
            if (size > 0) {
                for (i in 0 until size) {
                    delPoint()
                }
            } else if (size < 0) {
                for (i in -size downTo 1) {
                    addPoint()
                }
            }
        }

    /**
     * 贝塞尔曲线阶数
     *
     * @return
     */
    val orderStr: String
        get() {
            var str = ""
            when (order) {
                1 -> str = "一"
                2 -> str = "二"
                3 -> str = "三"
                4 -> str = "四"
                5 -> str = "五"
                6 -> str = "六"
                7 -> str = "七"
                else -> {
                }
            }
            return str
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        // 初始坐标
        mControlPoints = ArrayList(MAX_COUNT + 1)
        val w = resources.displayMetrics.widthPixels
        mControlPoints!!.add(PointF((w / 5).toFloat(), (w / 5).toFloat()))
        mControlPoints!!.add(PointF((w / 3).toFloat(), (w / 2).toFloat()))
        mControlPoints!!.add(PointF((w / 3 * 2).toFloat(), (w / 4).toFloat()))

        // 贝塞尔曲线画笔
        mBezierPaint = Paint()
        mBezierPaint!!.color = Color.RED
        mBezierPaint!!.strokeWidth = BEZIER_WIDTH.toFloat()
        mBezierPaint!!.style = Paint.Style.STROKE
        mBezierPaint!!.isAntiAlias = true

        // 移动点画笔
        mMovingPaint = Paint()
        mMovingPaint!!.color = Color.BLACK
        mMovingPaint!!.isAntiAlias = true
        mMovingPaint!!.style = Paint.Style.FILL

        // 控制点画笔
        mControlPaint = Paint()
        mControlPaint!!.color = Color.BLACK
        mControlPaint!!.isAntiAlias = true
        mControlPaint!!.style = Paint.Style.STROKE

        // 切线画笔
        mTangentPaint = Paint()
        mTangentPaint!!.color = Color.parseColor(TANGENT_COLORS[0])
        mTangentPaint!!.isAntiAlias = true
        mTangentPaint!!.strokeWidth = TANGENT_WIDTH.toFloat()
        mTangentPaint!!.style = Paint.Style.FILL

        // 固定线画笔
        mLinePaint = Paint()
        mLinePaint!!.color = Color.LTGRAY
        mLinePaint!!.strokeWidth = CONTROL_WIDTH.toFloat()
        mLinePaint!!.isAntiAlias = true
        mLinePaint!!.style = Paint.Style.FILL

        // 点画笔
        mTextPointPaint = Paint()
        mTextPointPaint!!.color = Color.BLACK
        mTextPointPaint!!.isAntiAlias = true
        mTextPointPaint!!.textSize = TEXT_SIZE.toFloat()

        // 文字画笔
        mTextPaint = Paint()
        mTextPaint!!.color = Color.GRAY
        mTextPaint!!.isAntiAlias = true
        mTextPaint!!.textSize = TEXT_SIZE.toFloat()

        mBezierPath = Path()

        mState = mState or (STATE_READY or STATE_TOUCH)
    }

    /**
     * 创建Bezier点集
     *
     * @return
     */
    private fun buildBezierPoints(): ArrayList<PointF> {
        val points = ArrayList<PointF>()
        val order = mControlPoints!!.size - 1
        val delta = 1.0f / FRAME
        var t = 0f
        while (t <= 1) {
            // Bezier点集
            points.add(PointF(deCasteljauX(order, 0, t), deCasteljauY(order, 0, t)))
            t += delta
        }
        return points
    }

    /**
     * 创建切线点集
     */
    private fun buildTangentPoints(): ArrayList<ArrayList<ArrayList<PointF>>> {
        var points: ArrayList<PointF>   // 1条线点集
        var morepoints: ArrayList<ArrayList<PointF>>    // 多条线点集
        val allpoints = ArrayList<ArrayList<ArrayList<PointF>>>()  // 所有点集
        var point: PointF
        val order = mControlPoints!!.size - 1
        val delta = 1.0f / FRAME
        for (i in 0 until order - 1) {
            val size = allpoints.size
            morepoints = ArrayList()
            for (j in 0 until order - i) {
                points = ArrayList()
                var t = 0f
                while (t <= 1) {
                    var p0x = 0f
                    var p1x = 0f
                    var p0y = 0f
                    var p1y = 0f
                    val z = (t * FRAME).toInt()
                    if (size > 0) {
                        p0x = allpoints[i - 1][j][z].x
                        p1x = allpoints[i - 1][j + 1][z].x
                        p0y = allpoints[i - 1][j][z].y
                        p1y = allpoints[i - 1][j + 1][z].y
                    } else {
                        p0x = mControlPoints!![j].x
                        p1x = mControlPoints!![j + 1].x
                        p0y = mControlPoints!![j].y
                        p1y = mControlPoints!![j + 1].y
                    }
                    val x = (1 - t) * p0x + t * p1x
                    val y = (1 - t) * p0y + t * p1y
                    point = PointF(x, y)
                    points.add(point)
                    t += delta
                }
                morepoints.add(points)
            }
            allpoints.add(morepoints)
        }

        return allpoints
    }

    /**
     * deCasteljau算法
     *
     * @param i 阶数
     * @param j 点
     * @param t 时间
     * @return
     */
    private fun deCasteljauX(i: Int, j: Int, t: Float): Float {
        return if (i == 1) {
            (1 - t) * mControlPoints!![j].x + t * mControlPoints!![j + 1].x
        } else (1 - t) * deCasteljauX(i - 1, j, t) + t * deCasteljauX(i - 1, j + 1, t)
    }

    /**
     * deCasteljau算法
     *
     * @param i 阶数
     * @param j 点
     * @param t 时间
     * @return
     */
    private fun deCasteljauY(i: Int, j: Int, t: Float): Float {
        return if (i == 1) {
            (1 - t) * mControlPoints!![j].y + t * mControlPoints!![j + 1].y
        } else (1 - t) * deCasteljauY(i - 1, j, t) + t * deCasteljauY(i - 1, j + 1, t)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mWidth == 0 || mHeight == 0) {
            mWidth = width
            mHeight = height
        }
    }

    /**
     * 判断坐标是否在合法区域中
     *
     * @param x
     * @param y
     * @return
     */
    private fun isLegalTouchRegion(x: Float, y: Float): Boolean {
        if (x <= REGION_WIDTH || x >= mWidth - REGION_WIDTH || y <= REGION_WIDTH || y >= mHeight - REGION_WIDTH) {
            return false
        }
        val rectF = RectF()
        for (point in mControlPoints!!) {
            if (mCurPoint != null && mCurPoint == point) { // 判断是否是当前控制点
                continue
            }
            rectF.set(point.x - REGION_WIDTH, point.y - REGION_WIDTH, point.x + REGION_WIDTH, point.y + REGION_WIDTH)
            if (rectF.contains(x, y)) {
                return false
            }
        }
        return true
    }

    /**
     * 获取合法控制点
     *
     * @param x
     * @param y
     * @return
     */
    private fun getLegalControlPoint(x: Float, y: Float): PointF? {
        val rectF = RectF()
        for (point in mControlPoints!!) {
            rectF.set(point.x - REGION_WIDTH, point.y - REGION_WIDTH, point.x + REGION_WIDTH, point.y + REGION_WIDTH)
            if (rectF.contains(x, y)) {
                return point
            }
        }
        return null
    }


    /**
     * 判断手指坐标是否在合法区域中
     *
     * @param x
     * @param y
     * @return
     */
    private fun isLegalFingerRegion(x: Float, y: Float): Boolean {
        if (mCurPoint != null) {
            val rectF = RectF(mCurPoint!!.x - FINGER_RECT_SIZE / 2, mCurPoint!!.y - FINGER_RECT_SIZE / 2, mCurPoint!!
                    .x + FINGER_RECT_SIZE / 2, mCurPoint!!.y + FINGER_RECT_SIZE / 2)
            if (rectF.contains(x, y)) {
                return true
            }
        }
        return false
    }

    override fun onDraw(canvas: Canvas) {
        if (isRunning && !isTouchable) {
            if (mBezierPoint == null) {
                mBezierPath!!.reset()
                mBezierPoint = mBezierPoints!![0]
                mBezierPath!!.moveTo(mBezierPoint!!.x, mBezierPoint!!.y)
            }
            // 控制点和控制点连线
            val size = mControlPoints!!.size
            var point: PointF
            for (i in 0 until size) {
                point = mControlPoints!![i]
                if (i > 0) {
                    // 控制点连线
                    canvas.drawLine(mControlPoints!![i - 1].x, mControlPoints!![i - 1].y, point.x, point.y,
                            mLinePaint!!)
                }
                // 控制点
                canvas.drawCircle(point.x, point.y, CONTROL_RADIUS.toFloat(), mControlPaint!!)
                // 控制点文本
                canvas.drawText("p$i", point.x + CONTROL_RADIUS * 2, point.y + CONTROL_RADIUS * 2, mTextPointPaint!!)
                // 控制点文本展示
                canvas.drawText("p" + i + " ( " + DecimalFormat("##0.0").format(point.x.toDouble()) + " , " + DecimalFormat("##0.0").format(point.y.toDouble()) + ") ", REGION_WIDTH.toFloat(), (mHeight - (size - i) * TEXT_HEIGHT).toFloat(), mTextPaint!!)

            }

            // 切线
            if (mTangent && mInstantTangentPoints != null && !isStop) {
                val tsize = mInstantTangentPoints!!.size
                var tps: ArrayList<PointF>
                for (i in 0 until tsize) {
                    tps = mInstantTangentPoints!![i]
                    val tlen = tps.size
                    for (j in 0 until tlen - 1) {
                        mTangentPaint!!.color = Color.parseColor(TANGENT_COLORS[i])
                        canvas.drawLine(tps[j].x, tps[j].y, tps[j + 1].x, tps[j + 1].y,
                                mTangentPaint!!)
                        canvas.drawCircle(tps[j].x, tps[j].y, CONTROL_RADIUS.toFloat(), mTangentPaint!!)
                        canvas.drawCircle(tps[j + 1].x, tps[j + 1].y, CONTROL_RADIUS.toFloat(), mTangentPaint!!)
                    }
                }
            }

            // Bezier曲线
            mBezierPath!!.lineTo(mBezierPoint!!.x, mBezierPoint!!.y)
            canvas.drawPath(mBezierPath!!, mBezierPaint!!)
            // Bezier曲线起始移动点
            canvas.drawCircle(mBezierPoint!!.x, mBezierPoint!!.y, CONTROL_RADIUS.toFloat(), mMovingPaint!!)
            // 时间展示
            canvas.drawText("t:" + DecimalFormat("##0.000").format((mR.toFloat() / FRAME).toDouble()), (mWidth - TEXT_HEIGHT * 3).toFloat(), (mHeight - TEXT_HEIGHT).toFloat(), mTextPaint!!)

            mHandler.removeMessages(HANDLER_WHAT)
            mHandler.sendEmptyMessage(HANDLER_WHAT)
        }
        if (isTouchable) {
            // 控制点和控制点连线
            val size = mControlPoints!!.size
            var point: PointF
            for (i in 0 until size) {
                point = mControlPoints!![i]
                if (i > 0) {
                    canvas.drawLine(mControlPoints!![i - 1].x, mControlPoints!![i - 1].y, point.x, point.y,
                            mLinePaint!!)
                }
                canvas.drawCircle(point.x, point.y, CONTROL_RADIUS.toFloat(), mControlPaint!!)
                canvas.drawText("p$i", point.x + CONTROL_RADIUS * 2, point.y + CONTROL_RADIUS * 2, mTextPointPaint!!)
                canvas.drawText("p" + i + " ( " + DecimalFormat("##0.0").format(point.x.toDouble()) + " , " + DecimalFormat("##0.0").format(point.y.toDouble()) + ") ", REGION_WIDTH.toFloat(), (mHeight - (size - i) * TEXT_HEIGHT).toFloat(), mTextPaint!!)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (!isTouchable) {
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> mState = mState and STATE_READY.inv()
            MotionEvent.ACTION_MOVE -> {
                val x = event.x
                val y = event.y
                if (mCurPoint == null) {
                    mCurPoint = getLegalControlPoint(x, y)
                }
                if (mCurPoint != null && isLegalTouchRegion(x, y)) {  // 判断手指移动区域是否合法
                    if (isLegalFingerRegion(x, y)) {    // 判断手指触摸区域是否合法
                        mCurPoint!!.x = x
                        mCurPoint!!.y = y
                        invalidate()
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                mCurPoint = null
                mState = mState or STATE_READY
            }
        }
        return true
    }

    /**
     * 开始
     */
    fun start() {
        if (isReady) {
            mBezierPoint = null
            mInstantTangentPoints = null
            mBezierPoints = buildBezierPoints()
            if (mTangent) {
                mTangentPoints = buildTangentPoints()
            }
            mState = mState and STATE_READY.inv()
            mState = mState and STATE_TOUCH.inv()
            mState = mState or STATE_RUNNING
            invalidate()
        }
    }

    /**
     * 停止
     */
    fun stop() {
        if (isRunning) {
            mHandler.removeMessages(HANDLER_WHAT)
            mR = 0
            mState = mState and STATE_RUNNING.inv()
            mState = mState and STATE_STOP.inv()
            mState = mState or (STATE_READY or STATE_TOUCH)
            invalidate()
        }
    }

    /**
     * 添加控制点
     */
    fun addPoint(): Boolean {
        if (isReady) {
            val size = mControlPoints!!.size
            if (size >= MAX_COUNT + 1) {
                return false
            }
            val x = mControlPoints!![size - 1].x
            val y = mControlPoints!![size - 1].y
            val r = mWidth / 5
            val region = arrayOf(floatArrayOf(0f, r.toFloat()), floatArrayOf(0f, (-r).toFloat()), floatArrayOf(r.toFloat(), r.toFloat()), floatArrayOf((-r).toFloat(), (-r).toFloat()), floatArrayOf(r.toFloat(), 0f), floatArrayOf((-r).toFloat(), 0f), floatArrayOf(0f, 1.5f * r), floatArrayOf(0f, -1.5f * r), floatArrayOf(1.5f * r, 1.5f * r), floatArrayOf(-1.5f * r, -1.5f * r), floatArrayOf(1.5f * r, 0f), floatArrayOf(-1.5f * r, 0f), floatArrayOf(0f, (2 * r).toFloat()), floatArrayOf(0f, (-2 * r).toFloat()), floatArrayOf((2 * r).toFloat(), (2 * r).toFloat()), floatArrayOf((-2 * r).toFloat(), (-2 * r).toFloat()), floatArrayOf((2 * r).toFloat(), 0f), floatArrayOf((-2 * r).toFloat(), 0f))
            var t = 0
            val len = region.size
            while (true) {  // 随机赋值
                t++
                if (t > len) {  // 超出region长度，跳出随机赋值
                    t = 0
                    break
                }
                val rand = Random().nextInt(len)
                val px = x + region[rand][0]
                val py = y + region[rand][1]
                if (isLegalTouchRegion(px, py)) {
                    mControlPoints!!.add(PointF(px, py))
                    invalidate()
                    break
                }
            }
            if (t == 0) {   // 超出region长度而未赋值时，循环赋值
                for (i in 0 until len) {
                    val px = x + region[i][0]
                    val py = y + region[i][1]
                    if (isLegalTouchRegion(px, py)) {
                        mControlPoints!!.add(PointF(px, py))
                        invalidate()
                        break
                    }
                }
            }
            return true
        }
        return false
    }

    /**
     * 删除控制点
     */
    fun delPoint(): Boolean {
        if (isReady) {
            val size = mControlPoints!!.size
            if (size <= 2) {
                return false
            }
            mControlPoints!!.removeAt(size - 1)
            invalidate()
            return true
        }
        return false
    }

    /**
     * 设置移动速率
     *
     * @param rate
     */
    fun setRate(rate: Int) {
        mRate = rate
    }

    /**
     * 设置是否显示切线
     *
     * @param tangent
     */
    fun setTangent(tangent: Boolean) {
        mTangent = tangent
    }

    /**
     * 设置是否循环
     *
     * @param loop
     */
    fun setLoop(loop: Boolean) {
        mLoop = loop
    }

    companion object {

        private val MAX_COUNT = 7  // 贝塞尔曲线最大阶数
        private val REGION_WIDTH = 30  // 合法区域宽度
        private val FINGER_RECT_SIZE = 60   // 矩形尺寸
        private val BEZIER_WIDTH = 10   // 贝塞尔曲线线宽
        private val TANGENT_WIDTH = 6  // 切线线宽
        private val CONTROL_WIDTH = 12    // 控制点连线线宽
        private val CONTROL_RADIUS = 12  // 控制点半径
        private val TEXT_SIZE = 40    // 文字画笔尺寸
        private val TEXT_HEIGHT = 60  // 文本高度
        private val RATE = 10 // 移动速率
        private val HANDLER_WHAT = 100
        private val FRAME = 1000  // 1000帧
        private val TANGENT_COLORS = arrayOf("#7fff00", "#7a67ee", "#ee82ee", "#ffd700", "#1c86ee", "#8b8b00")  // 切线颜色
        private val STATE_READY = 0x0001
        private val STATE_RUNNING = 0x0002
        private val STATE_STOP = 0x0004
        private val STATE_TOUCH = 0x0010
    }

}
