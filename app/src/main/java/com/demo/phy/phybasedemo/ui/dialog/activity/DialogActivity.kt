package com.demo.phy.phybasedemo.ui.dialog.activity

import android.content.Context
import android.content.Intent
import android.view.View
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.DialogPresenter
import com.demo.phy.phybasedemo.mvpview.DialogView
import com.demo.phy.phybasedemo.ui.dialog.view.OptionMenuDialog
import kotlinx.android.synthetic.main.top_bar_layout.*

class DialogActivity : BaseActivity<DialogView, DialogPresenter>(), DialogView {

    override fun getLayoutId(): Int {
        return R.layout.activity_dialog
    }

    override fun get_Presenter(): DialogPresenter {
        return DialogPresenter(this)
    }

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, DialogActivity::class.java)
            context.startActivity(intent)
        }

    }

    lateinit var mOptionMenuDialog : OptionMenuDialog

    override fun initView() {
        left_back.visibility = View.VISIBLE
        center_title.visibility = View.VISIBLE
        center_title.text = "弹框"
        right_icon.visibility = View.VISIBLE

        mOptionMenuDialog = OptionMenuDialog(this@DialogActivity)
    }

    override fun initData() {

    }

    override fun initListener() {
        left_back.setOnClickListener {
            onBackPressed()
        }

        right_icon.setOnClickListener {
            mOptionMenuDialog.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }


}