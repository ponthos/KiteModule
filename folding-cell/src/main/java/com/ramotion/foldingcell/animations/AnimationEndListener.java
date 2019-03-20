package com.ramotion.foldingcell.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;

import com.jiayuan.jr.basemodule.AdvancePathView;
import com.q42.android.scrollingimageview.ScrollingImageView;
import com.ramotion.foldingcell.FoldingCell;

/**
 * Just sugar for code clean
 */
public abstract class AnimationEndListener implements Animation.AnimationListener {

    @Override
    public void onAnimationStart(Animation animation) {
        // do nothing
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // do nothing
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        // do nothing
    }
    public void function(FoldingCell fc, ScrollingImageView scrollingBackground, AdvancePathView advance){
        scrollingBackground.start();
        moveEnvelope(fc,advance.pointFEnd,advance.pointFFirst,advance.pointFStart,advance);
    }
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
    public void moveEnvelope(final View view,PointF pointFStart,PointF pointFFirst,PointF pointFEnd,AdvancePathView advance){
        ValueAnimator animator = ValueAnimator.ofObject(new BezierEvaluator(pointFFirst), pointFStart, pointFEnd);
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
                ((ViewGroup)view.getParent()).removeView(view);
                advance.addHeart();
            }
        });
        ObjectAnimator af = ObjectAnimator.ofFloat(view, "alpha", 1f, 0);
        af.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0);
        AnimatorSet set = new AnimatorSet();
        set.setInterpolator(new AccelerateInterpolator());
        set.setDuration(5000);
        set.play(animator).with(af).with(scaleX).with(scaleY);
        set.start();

    }
}
