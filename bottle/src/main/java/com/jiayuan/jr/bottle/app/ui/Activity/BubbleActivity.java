package com.jiayuan.jr.bottle.app.ui.Activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jiayuan.jr.bottle.R;
import com.jiayuan.jr.bottle.app.utils.HearView;

import java.io.InputStream;

@Route(path = "/bottle/bubble")
public class BubbleActivity extends BaseActivity {

    @Override
    public void setupActivityComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.bottlemodule_activity_bubble;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }
    @SuppressLint("ResourceType")
    @Override
    protected void onResume() {
        super.onResume();
        HearView layout = findViewById(R.id.high);
        InputStream is ;
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        opt.inSampleSize = 2;
        is= getResources().openRawResource(R.drawable.bg_pop);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(getResources(), bm);
        layout.setBackgroundDrawable(bd);
    }

}
