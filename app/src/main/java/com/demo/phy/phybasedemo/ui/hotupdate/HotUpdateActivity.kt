package com.demo.phy.phybasedemo.ui.hotupdate

import android.content.Context
import android.content.Intent
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.HotUpdatePresenter
import com.demo.phy.phybasedemo.mvpview.HotUpdateView
import kotlinx.android.synthetic.main.top_bar_layout.*

/**
 * Created by phy on 2019/11/29
 */
class HotUpdateActivity : BaseActivity<HotUpdateView, HotUpdatePresenter>(), HotUpdateView {

    override fun getLayoutId(): Int {
        return R.layout.activity_hot_update
    }

    override fun get_Presenter(): HotUpdatePresenter {
        return HotUpdatePresenter(this)
    }

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, HotUpdateActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
        center_title.text = "热更新(待定中)"
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}