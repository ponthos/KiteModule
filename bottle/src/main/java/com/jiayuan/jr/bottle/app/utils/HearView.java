package com.jiayuan.jr.bottle.app.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jiayuan.jr.bottle.R;

import java.util.Random;

public class HearView extends RelativeLayout {

    protected Random random;
    private Path mPath;
    private Paint mPaint;
    protected PointF pointFStart, pointFEnd, pointFFirst, pointFSecond;
    protected Bitmap bitmap;

    private int[]colors ={Color.WHITE,Color.CYAN,Color.YELLOW,Color.BLACK ,Color.LTGRAY,Color.GREEN,Color.RED};

    public HearView(Context context) {
        super(context);
        initView();
    }

    public HearView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public HearView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {


        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(10);

        mPath = new Path();

        pointFStart = new PointF();
        pointFFirst = new PointF();
        pointFSecond = new PointF();
        pointFEnd = new PointF();

        random = new Random();
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pop);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointFStart = new PointF();
        pointFFirst = new PointF();
        pointFSecond = new PointF();
        pointFEnd = new PointF();

        pointFStart.x = getMeasuredWidth() / 2 - bitmap.getWidth() / 2;
        pointFStart.y = getMeasuredHeight() - bitmap.getHeight();

        pointFEnd.y = 0;
        pointFEnd.x = random.nextFloat() * getMeasuredWidth();

        pointFFirst.x = random.nextFloat() * getMeasuredWidth();
        pointFSecond.x = getMeasuredWidth() - pointFFirst.x;
        pointFSecond.y = random.nextFloat() * getMeasuredHeight() / 2 + getMeasuredHeight() / 2;
        pointFFirst.y = random.nextFloat() * getMeasuredHeight() / 2;
        Log.i("TAG", "出发了");
        addHeart();
        return true;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(pointFFirst.x, pointFFirst.y, 1, mPaint);
        canvas.drawCircle(pointFSecond.x, pointFSecond.y, 1, mPaint);
        canvas.drawCircle(pointFEnd.x, pointFEnd.y, 1, mPaint);
        canvas.drawCircle(pointFStart.x, pointFStart.y, 1, mPaint);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.TRANSPARENT);
        mPath.moveTo(pointFStart.x, pointFStart.y);
        mPath.cubicTo(pointFFirst.x, pointFFirst.y, pointFSecond.x, pointFSecond.y, pointFEnd.x, pointFEnd.y);
        canvas.drawPath(mPath, mPaint);
        mPath.reset();
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


//        Button button = new Button(getContext());
//        button.setText("风筝起飞");
//
//        button.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addHeart();
//            }
//
//        addView(button);
    }

    public void addHeart() {
        TextView imageView = new TextView(getContext());
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(CENTER_HORIZONTAL);
        params.addRule(ALIGN_PARENT_BOTTOM);
//        imageView.setImageBitmap(drawHeart(colors[random.nextInt(colors.length)]));
        imageView.setBackground(getResources().getDrawable(R.drawable.pop));
        imageView.setText("我是文字");
        imageView.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL);
        addView(imageView, params);
        moveHeart(imageView);
    }

    private void moveHeart(final TextView view){
        PointF pointFFirst = this.pointFFirst;
        PointF pointFSecond = this.pointFSecond;
        PointF pointFStart = this.pointFStart;
        PointF pointFEnd = this.pointFEnd;


        ValueAnimator animator = ValueAnimator.ofObject(new HearView.TypeE(pointFFirst, pointFSecond), pointFStart, pointFEnd);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF value = (PointF) animation.getAnimatedValue();
                view.setX(value.x);
                view.setY(value.y);
            }
        });

        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                HearView.this.removeView(view);
            }
        });

        ObjectAnimator af = ObjectAnimator.ofFloat(view, "alpha", 1f, 0);
        af.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                HearView.this.removeView(view);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(5000);
        set.play(animator).with(af);
        set.start();

    }

    /**
     * 绘制一个增值器
     */
    class TypeE implements TypeEvaluator<PointF> {

        private PointF pointFFirst,pointFSecond;

        public TypeE(PointF start,PointF end){
            this.pointFFirst =start;
            this.pointFSecond = end;
        }

        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            PointF result = new PointF();
            float left = 1 - fraction;
            result.x = (float) (startValue.x*Math.pow(left,3)+3*pointFFirst.x*Math.pow(left,2)*fraction+3*pointFSecond.x*Math.pow(fraction, 2)*left+endValue.x*Math.pow(fraction,3));
            result.y= (float) (startValue.y*Math.pow(left,3)+3*pointFFirst.y*Math.pow(left,2)*fraction+3*pointFSecond.y*Math.pow(fraction, 2)*left+endValue.y*Math.pow(fraction,3));
            return result;
        }
    }
}