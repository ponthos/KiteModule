package com.jiayuan.jr.bottle.app.ui.Activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jiayuan.jr.bottle.R;
import com.q42.android.scrollingimageview.ScrollingImageView;

import butterknife.BindView;

@Route(path = "/bottle/bessel")
public class BesselActivity extends BaseActivity {
    ImageView bg_image;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.bottlemodule_activity_bessel);
//    }

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.bottlemodule_activity_bessel;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        bg_image=findViewById(R.id.bg_image);
        ScrollingImageView scrollingBackground = (ScrollingImageView) findViewById(R.id.scrolling_background);
        scrollingBackground.stop();
        scrollingBackground.start();

    }
}
