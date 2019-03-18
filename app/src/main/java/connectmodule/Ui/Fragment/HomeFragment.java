package connectmodule.Ui.Fragment;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jiayuan.jr.bannermodule.Banner;
import com.jiayuan.jr.bannermodule.BannerConfig;
import com.jiayuan.jr.bannermodule.Transformer;
import com.jiayuan.jr.kiteshell.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import connectmodule.Api;
import connectmodule.GlideImageLoader;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner banner;
    List<String> images=new ArrayList<String>();
    List<String> titles=new ArrayList<String>();
    //如果你需要考虑更好的体验，可以这么操作
    @Override
    public void onStart() {
        super.onStart();
        //开始轮播
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {

    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        titles.add("网络图片一");
        titles.add("网络图片二");
        titles.add("网络图片三");
        images.add(Api.pic_1);
        images.add(Api.pic_2);
        images.add(Api.pic_3);
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

    @Override
    public void setData(@Nullable Object data) {

    }
}
