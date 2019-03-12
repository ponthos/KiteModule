package connectmodule.Ui.Activity;
//

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;
import android.widget.TextView;

import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BasePresenter;
import com.jiayuan.jr.bannermodule.Banner;
import com.jiayuan.jr.bannermodule.BannerConfig;
import com.jiayuan.jr.bannermodule.Transformer;
import com.jiayuan.jr.kiteshell.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import connectmodule.Api;
import connectmodule.Component.DaggerUserComponent;
import connectmodule.Contract.UserContract;
import connectmodule.GlideImageLoader;
import connectmodule.Module.UserModule;
import connectmodule.Ui.Fragment.HomeFragment;
import connectmodule.Ui.Fragment.MineFragment;
import connectmodule.Ui.Fragment.OtherFragment;

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
    @BindView(R.id.image)
    ImageView image_view;
//    @BindView(R.id.banner)
    Banner banner;

    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.other)
    TextView other;
    @BindView(R.id.mine)
    TextView mine;
    @Override
    public int initView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }
    List<String> images=new ArrayList<String>();
    List<String> titles=new ArrayList<String>();
    Fragment homeFragment=new HomeFragment();
    Fragment otherFragment=new OtherFragment();
    Fragment mineFragment=new MineFragment();
    Fragment[] fragment=new Fragment[]{homeFragment,otherFragment,mineFragment};
    FragmentManager fragmentManager=getSupportFragmentManager();
    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titles.add("网络图片一");
        titles.add("网络图片二");
        titles.add("网络图片三");
        images.add(Api.pic_1);
        images.add(Api.pic_2);
        images.add(Api.pic_3);
        banner=findViewById(R.id.banner);
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
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
    private void selectTab(int i){
        hideAllFragment(fragmentTransaction);
        if(fragment[i].isAdded()){
            fragmentTransaction.show(fragment[i]);
        }else{
            fragmentTransaction.add(R.id.fragment,fragment[i]);
            fragmentTransaction.show(fragment[i]);
        }
        fragmentTransaction.commit();
    }
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        for(int i=0;i<fragment.length;i++){
            fragmentTransaction.hide(fragment[i]);
        }
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
