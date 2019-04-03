package com.jiayuan.jr.basemodule;

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
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.q42.android.scrollingimageview.ScrollingImageView;

import java.util.Random;

public class AdvancePathView extends RelativeLayout {

    protected Random random;
    private Path mPath;
    private Paint mPaint;
    public PointF pointFStart, pointFEnd, pointFFirst, pointFSecond;
    protected Bitmap bitmap;
    private int[]colors ={Color.WHITE,Color.CYAN,Color.YELLOW,Color.BLACK ,Color.LTGRAY,Color.GREEN,Color.RED};

    public AdvancePathView(Context context) {
        super(context);
        initView();
    }

    public AdvancePathView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public AdvancePathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    ImageView imageView;
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
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.timg);
    }
    protected void initPoint() {
//        WindowManager wm = getContext().getSystemService(Context.WINDOW_SERVICE)
//        val width = wm.getDefaultDisplay().getWidth();
//        val height = wm.getDefaultDisplay().getHeight();
        pointFStart.x = getMeasuredWidth() * 2/ 3;
        pointFStart.y = getMeasuredHeight()*3/4;

        pointFEnd.y = 10;
        pointFEnd.x = 1;

        pointFSecond.x= 10;
        pointFSecond.y= getMeasuredHeight() * 3 / 4;

        pointFFirst.x = getMeasuredWidth() - 10;
        pointFFirst.y = getMeasuredHeight() / 4;
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initPoint();
//        imageView= new ImageView(getContext());
//        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        params.addRule(ALIGN_PARENT_RIGHT);
//        params.addRule(ALIGN_PARENT_BOTTOM);
//        params.addRule(ALIGN_PARENT_END);
//        params.setMargins(0,0,getMeasuredWidth()/3,getMeasuredHeight()/4);
//        imageView.setImageBitmap(bitmap);
//        addView(imageView, params);


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

    private Bitmap drawHeart(int color) {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(bitmap, 0, 0, mPaint);
        canvas.drawColor(color, PorterDuff.Mode.SRC_ATOP);
        canvas.setBitmap(null);
        return newBitmap;
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
//        });
//
//        addView(button);
    }

    public void addHeart(ImageView imageView, ScrollingImageView scrollingImageView) {

        moveHeart(imageView,scrollingImageView);
    }
//    //去掉覆盖颜色2019/03/17
//    public void addHeart() {
//        moveHeart(imageView);
//    }


    public void moveHeart(final ImageView view, final ScrollingImageView scrollingImageView){
        PointF pointFFirst = this.pointFFirst;
        PointF pointFSecond = this.pointFSecond;
        PointF pointFStart = this.pointFStart;
        PointF pointFEnd = this.pointFEnd;
//        view.setPivotX(view.getWidth()/2);
//        view.setPivotY(view.getHeight()/2);

        ValueAnimator animator = ValueAnimator.ofObject(new TypeE(pointFFirst, pointFSecond), pointFStart, pointFEnd);
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
                AdvancePathView.this.removeView(view);
                scrollingImageView.stop();
            }
        });
//        ObjectAnimator af = ObjectAnimator.ofFloat(view, "alpha", 1f, 0);
//        af.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                AdvancePathView.this.removeView(view);
//            }
//        });
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(5000);
        set.play(animator).with(scaleX).with(scaleY);
        set.start();

    }
    //缩放的变化动画
    public static  void scaleAnimate(final View view ,float start ,final float end ,final int duration)
    {
        final ValueAnimator valueAnimator = ValueAnimator.ofFloat(start , end);
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                //这里在值变化的时候做相应的动作，我们是缩放图片，注意先设置缩放的起始点，坐标都是以view的左上角为原点
                PointF value = (PointF) animation.getAnimatedValue();
                view.setX(value.x);
                view.setY(value.y);
            }
        });
        //动画完成后有个缩小的动作
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                //这里写是有问题的，递归调用会重新启动监听，然后又会启动，因为动画只启动一次的缘故，但是会持续执行onUpdateListener，且value值一直是0.95
//             scaleAnimate(view, end, 0.95f, duration / 3);
                ScaleAnimation scaleAnimation = new ScaleAnimation(1 , 0.95f , 1 , 0.95f , view.getWidth()/2 , view.getHeight()/2);
                scaleAnimation.setDuration(duration);
                scaleAnimation.setFillAfter(true);//这是保证动画后的效果，不然会恢复到原来大小。
                view.startAnimation(scaleAnimation);
            }
        });
        valueAnimator.start();
    }
//    public void moveEnvelope(final View view){
//        PointF pointFFirst = this.pointFFirst;
//        PointF pointFStart =  this.pointFEnd;
//        PointF pointFEnd =this.pointFStart;
//
//
//        ValueAnimator animator = ValueAnimator.ofObject(new BezierEvaluator(pointFFirst), pointFStart, pointFEnd);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                PointF value = (PointF) animation.getAnimatedValue();
//                view.setPivotX(view.getWidth());
//                view.setPivotY(view.getHeight());
//                view.setX(value.x);
//                view.setY(value.y);
//                //这里在值变化的时候做相应的动作，我们是缩放图片，注意先设置缩放的起始点，坐标都是以view的左上角为原点
////                PointF valuef = (PointF) animation.getAnimatedValue();
////                view.setPivotX(view.getWidth()/2);
////                view.setPivotY(view.getHeight()/2);
////                view.setScaleX(valuef.x);
////                view.setScaleY(valuef.y);
//            }
//        });
//
//        animator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                AdvancePathView.this.removeView(view);
//                AdvancePathView.this.removeView(view);
//                ScaleAnimation scaleAnimation = new ScaleAnimation(1 , 0.95f , 1 , 0.95f , view.getWidth()/2 , view.getHeight()/2);
//                scaleAnimation.setFillAfter(true);//这是保证动画后的效果，不然会恢复到原来大小。
//                view.startAnimation(scaleAnimation);
//                addHeart();
//            }
//        });
//
//        ObjectAnimator af = ObjectAnimator.ofFloat(view, "alpha", 1f, 0);
//        af.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//
//            }
//        });
//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0.5f , 0.5f);
//        valueAnimator.setDuration(5000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                //这里在值变化的时候做相应的动作，我们是缩放图片，注意先设置缩放的起始点，坐标都是以view的左上角为原点
//                float value = (float) animation.getAnimatedValue();
//                view.setPivotX(view.getWidth()/2);
//                view.setPivotY(view.getHeight()/2);
//                view.setScaleX(value);
//                view.setScaleY(value);
//            }
//        });  //动画完成后有个缩小的动作
//        valueAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                //这里写是有问题的，递归调用会重新启动监听，然后又会启动，因为动画只启动一次的缘故，但是会持续执行onUpdateListener，且value值一直是0.95
////             scaleAnimate(view, end, 0.95f, duration / 3);
////                ScaleAnimation scaleAnimation = new ScaleAnimation(1 , 0.95f , 1 , 0.95f , view.getWidth()/2 , view.getHeight()/2);
////                scaleAnimation.setDuration(5000);
////                scaleAnimation.setFillAfter(true);//这是保证动画后的效果，不然会恢复到原来大小。
////                view.startAnimation(scaleAnimation);
//            }
//        });
//        AnimatorSet set = new AnimatorSet();
//        set.setInterpolator(new AccelerateInterpolator());
//        set.setDuration(5000);
//        set.play(animator).with(af);
//        set.start();
//
//    }

    /**
     *估值器
     */
    public class BezierEvaluator implements TypeEvaluator<PointF> {

        private PointF controllPoint;

        public BezierEvaluator(PointF controllPoint) {
            this.controllPoint = controllPoint;
        }

        @Override
        public PointF evaluate(float t, PointF startValue, PointF endValue) {
//      贝塞尔曲线二阶公式
            PointF result = new PointF();
            result.x = (int) ((1 - t) * (1 - t) * startValue.x + 2 * t * (1 - t) * controllPoint.x + t * t * endValue.x);
            result.y = (int) ((1 - t) * (1 - t) * startValue.y + 2 * t * (1 - t) * controllPoint.y + t * t * endValue.y);
            return result;
        }


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