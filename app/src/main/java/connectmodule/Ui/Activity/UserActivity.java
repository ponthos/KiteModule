package connectmodule.Ui.Activity;
//

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BasePresenter;
import com.jiayuan.jr.kiteshell.R;

import butterknife.BindView;
import connectmodule.Component.DaggerUserComponent;
import connectmodule.Contract.UserContract;
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

    Fragment homeFragment;
    Fragment otherFragment;
    Fragment mineFragment;
    Fragment[] fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
//        mine=findViewById(R.id.mine);
        homeFragment=new HomeFragment();
        otherFragment=new OtherFragment();
        mineFragment=new MineFragment();
        fragment=new Fragment[]{homeFragment,otherFragment,mineFragment};
        selectTab(0);
        mine.setOnClickListener(
                v -> {
                    // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
//                    ARouter.getInstance().build("/test/activity").navigation();
                    ARouter.getInstance().build("/bottle/bessel").navigation();
//                    // 2. 跳转并携带参数
//                    ARouter.getInstance().build("/test/1")
//                            .withLong("key1", 666L)
//                            .withString("key3", "888")
//                            .withObject("key4", new Test("Jack", "Rose"))
//                            .navigation();
                }
        );
        home.setOnClickListener(v -> {
            selectTab(0);
        });
        other.setOnClickListener(v -> {
            ARouter.getInstance().build("/bottle/bubble").navigation();
        });
//        banner=findViewById(R.id.banner);

    }
    private void selectTab(int i){
        fragmentManager=getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();
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
//        fragmentTransaction.commit();
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
