package com.jiayuan.jr.kiteshell.Ui.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jess.arms.base.BaseFragment
import com.jess.arms.di.component.AppComponent
import com.jess.arms.mvp.IPresenter
import com.jiayuan.jr.kiteshell.R

class MineFragment : BaseFragment<IPresenter>() {
    override fun setupFragmentComponent(appComponent: AppComponent) {

    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun initData(savedInstanceState: Bundle?) {

    }

    override fun setData(data: Any?) {

    }
}
