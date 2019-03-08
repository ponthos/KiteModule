package connectmodule.Ui;
//

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BasePresenter;
import com.jiayuan.jr.kiteshell.R;
import com.jiayuan.jr.kiteshell.Utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;

import connectmodule.Api;
import connectmodule.Component.DaggerUserComponent;
import connectmodule.Contract.UserContract;
import connectmodule.Module.UserModule;

public class UserActivity extends BaseActivity<BasePresenter> implements UserContract.View {

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);

    }

    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }
    Banner banner;
    ArrayList<String> images=new ArrayList<String>();
    ArrayList<String> titles=new ArrayList<String>();
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titles.add("网络图片一");
        titles.add("网络图片二");
        titles.add("网络图片三");
        images.add(Api.pic_1);
        images.add(Api.pic_2);
        images.add(Api.pic_3);
        banner = (Banner) findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
    //如果你需要考虑更好的体验，可以这么操作
    @Override
    protected void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }


    @Override
    public void setAdapter(DefaultAdapter adapter) {

    }

    @Override
    public void startLoadMore() {

    }

    @Override
    public void endLoadMore() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }
}
