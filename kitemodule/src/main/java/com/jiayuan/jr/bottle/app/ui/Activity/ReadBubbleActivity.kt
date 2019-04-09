package com.jiayuan.jr.bottle.app.ui.Activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jess.arms.base.DefaultAdapter
import com.jess.arms.di.component.AppComponent
import com.jess.arms.utils.ArmsUtils
import com.jess.arms.utils.Preconditions.checkNotNull
import com.jiayuan.jr.bottle.mvp.model.Component.DaggerReadBubbleComponent
import com.jiayuan.jr.connectmodule.Contract.ReadBubbleContract
import com.jiayuan.jr.connectmodule.Module.ReadBubbleModule
import com.jiayuan.jr.connectmodule.Presenter.ReadBubblePresenter
import com.jiayuan.jr.kitemodule.R
import com.jiayuan.jr.kitemodule.R.drawable.bg_pop
import com.jiayuan.jr.modelmodule.ResponseModel.ArticResponse
import kotlinx.android.synthetic.main.kitemodulemodule_activity_bubble.*
import java.io.InputStream

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

@Suppress("DEPRECATION")
@Route(path = "/kite_module/read_bubble_activity")
class ReadBubbleActivity : BaseActivity<ReadBubblePresenter>(), ReadBubbleContract.View {
    override fun setArticles(articleResponses: MutableList<ArticResponse>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        this.read_bubble_view.getArticles(articleResponses);
    }

    override fun startLoadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun endLoadMore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAdapter(adapter: DefaultAdapter<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun setupActivityComponent(appComponent: AppComponent) {
        DaggerReadBubbleComponent
                .builder()
                .appComponent(appComponent)
                .readBubbleModule(ReadBubbleModule(this)) //请将ReadBubbleModule()第一个首字母改为小写
                .build()
                .inject(this)
    }
    override fun initView(savedInstanceState: Bundle?): Int {
        return R.layout.kitemodulemodule_activity_bubble
    }

    override fun initData(savedInstanceState: Bundle?) {
        var mPresenter = mPresenter
        (if (mPresenter != null) mPresenter else throw KotlinNullPointerException()).getArticle(1);
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
        checkNotNull(message)
        ArmsUtils.snackbarText(message)
    }

    override fun launchActivity(intent: Intent) {
        checkNotNull(intent)
        startActivity(intent)
    }

    override fun killMyself() {
        finish()
    }
    @SuppressLint("ResourceType")
    override fun onResume() {
        super.onResume()
        val `is`: InputStream = resources.openRawResource(bg_pop)
        val opt = BitmapFactory.Options()
        opt.inPreferredConfig = Bitmap.Config.ARGB_8888
        opt.inPurgeable = true
        opt.inInputShareable = true
        opt.inSampleSize = 2
        val bm = BitmapFactory.decodeStream(`is`, null, opt)
        val bd = BitmapDrawable(resources, bm)
        read_bubble_view.setBackgroundDrawable(bd)
    }

}
