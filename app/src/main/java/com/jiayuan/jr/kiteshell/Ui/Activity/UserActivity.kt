package com.jiayuan.jr.kiteshell.Ui.Activity

//

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.alibaba.android.arouter.launcher.ARouter
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.BasePresenter
import com.jiayuan.jr.bottle.mvp.model.Component.DaggerUserComponent
import com.jiayuan.jr.connectmodule.Contract.UserContract
import com.jiayuan.jr.connectmodule.Module.UserModule
import com.jiayuan.jr.kiteshell.R
import com.jiayuan.jr.kiteshell.Ui.BaseActivity
import com.jiayuan.jr.kiteshell.Ui.Fragment.HomeFragment
import com.jiayuan.jr.kiteshell.Ui.Fragment.MineFragment
import com.jiayuan.jr.kiteshell.Ui.Fragment.OtherFragment
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class UserActivity : BaseActivity<BasePresenter<*, *>>(), UserContract.View {
//
//    @BindView(R.id.home)
//     var home: TextView? = null
//    @BindView(R.id.other)
//     var other: TextView? = null
//    @BindView(R.id.mine)
//     var mine: TextView ?= null

    private lateinit var homeFragment: Fragment
    private lateinit var otherFragment: Fragment
    private lateinit var mineFragment: Fragment
    private lateinit var fragment: Array<Fragment>
    private lateinit var fragmentManager: FragmentManager
    private lateinit var fragmentTransaction: FragmentTransaction
//    homeFragment = HomeFragment()
//    otherFragment = OtherFragment()
//    mineFragment = MineFragment()
//    fragment = arrayOf(homeFragment, otherFragment, mineFragment)
    override fun setupActivityComponent(appComponent: AppComponent) {
    DaggerUserComponent
                .builder()
                .appComponent(appComponent)
                .userModule(UserModule(this))
                .build()
                .inject(this)
    }

    override fun initView(savedInstanceState: Bundle?): Int {

        return R.layout.activity_main
    }

    @Throws(NullPointerException::class)
    override fun initData(savedInstanceState: Bundle?) {
        mPresenter.toString();
//        mine=findViewById(R.id.mine);
        homeFragment = HomeFragment()
        otherFragment = OtherFragment()
        mineFragment = MineFragment()
        fragment = arrayOf(homeFragment, otherFragment, mineFragment)
        selectTab(0)
        var mine = mine
        (if (mine != null) mine else throw KotlinNullPointerException()).setOnClickListener {
            // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
            //                    ARouter.getInstance().build("/test/activity").navigation();
            ARouter.getInstance().build("/kite_module/bessel_activity").navigation()
            //                    // 2. 跳转并携带参数
            //                    ARouter.getInstance().build("/test/1")
            //                            .withLong("key1", 666L)
            //                            .withString("key3", "888")
            //                            .withObject("key4", new Test("Jack", "Rose"))
            //                            .navigation();
        }
        (if (home != null) home else throw NullPointerException("Expression 'home' must not be null")).setOnClickListener { selectTab(0) }
        var other = other
        (if (other != null) other else throw KotlinNullPointerException()).setOnClickListener { ARouter.getInstance().build("/kite_module/read_bubble_activity").navigation() }
//        other!!.setOnClickListener { ARouter.getInstance().build("/kite_module/bubble_activity").navigation() }
    }

    private fun selectTab(i: Int) {
        fragmentManager = supportFragmentManager
        fragmentTransaction = fragmentManager.beginTransaction()
        hideAllFragment(fragmentTransaction)
        if (fragment[i].isAdded) {
            fragmentTransaction.show(fragment[i])
        } else {
            fragmentTransaction.add(R.id.fragment, fragment[i])
            fragmentTransaction.show(fragment[i])
        }
        fragmentTransaction.commit()
    }

    private fun hideAllFragment(fragmentTransaction: FragmentTransaction) {
        for (i in fragment.indices) {
            fragmentTransaction.hide(fragment[i])
        }
        //        fragmentTransaction.commit();
    }


    override fun setAdapter(adapter: DefaultAdapter<*>) {

    }

    override fun startLoadMore() {

    }

    override fun endLoadMore() {

    }

    override fun showMessage(message: String) {

    }
}
