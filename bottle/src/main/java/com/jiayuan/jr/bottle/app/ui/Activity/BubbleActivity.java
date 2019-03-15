package com.jiayuan.jr.bottle.app.ui.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jiayuan.jr.bottle.R;
@Route(path = "/bottle/bubble")
public class BubbleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottlemodule_activity_bubble);
    }
}
