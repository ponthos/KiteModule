package com.ramotion.foldingcell.animations;

import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;

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
    public void function(FoldingCell fc, ScrollingImageView scrollingBackground, AdvancePathView advance,ImageView imageView){
        fc.setVisibility(View.GONE);
        scrollingBackground.start();
        advance.addView(imageView);
        advance.moveEnvelope(imageView);
    }
}
