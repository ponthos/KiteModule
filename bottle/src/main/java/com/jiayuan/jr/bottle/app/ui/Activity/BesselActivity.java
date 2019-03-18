package com.jiayuan.jr.bottle.app.ui.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jiayuan.jr.bottle.R;
import com.jiayuan.jr.bottle.app.utils.AdvancePathView;
import com.q42.android.scrollingimageview.ScrollingImageView;
import com.ramotion.foldingcell.FoldingCell;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

@Route(path = "/bottle/bessel")
public class BesselActivity extends BaseActivity {
    ImageView bg_image;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bottlemodule_activity_bessel);
//    }
    FoldingCell fc;
    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.bottlemodule_activity_bessel;
    }
    public AdvancePathView advance;
    ScrollingImageView scrollingBackground;
    TextView end;
    ImageView imageView;
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        bg_image=findViewById(R.id.bg_image);
        advance=findViewById(R.id.advance);
        fc =findViewById(R.id.folding_cell);
        scrollingBackground =findViewById(R.id.scrolling_background);
        end=findViewById(R.id.end);
        // get our folding cell

        // get our folding cell
//        final FoldingCell fc = (FoldingCell) findViewById(R.id.folding_cell);
        // set custom parameters
        //方法弃用修改为图片
//        fc.initialize(1000, Color.WHITE, 3);
//        fc.initialize(30, 1000, Color.DKGRAY, 2);
        // or with camera height parameter
        fc.initialize(100, 1000,getResources().getDrawable(R.drawable.timg), 3);
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
        imageView=new ImageView(this);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pop));
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fc.toggle(false);
                fc.setVisibility(View.GONE);
                scrollingBackground.start();
                advance.addView(imageView);
                advance.moveEnvelope(imageView);
                advance.addHeart();
            }
        });
        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                scrollingBackground.stop();
//                scrollingBackground.start();
                if(fc.getVisibility()!=View.GONE)
                fc.toggle(false);
            }
        });


    }

}
