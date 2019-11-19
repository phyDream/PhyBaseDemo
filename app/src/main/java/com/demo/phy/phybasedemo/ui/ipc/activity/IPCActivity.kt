package com.demo.phy.phybasedemo.ui.ipc.activity

import android.content.Context
import android.content.Intent
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.IPCPresenter
import com.demo.phy.phybasedemo.mvpview.IPCView
import com.demo.phy.phybasedemo.utils.ConfigInfo
import com.demo.phy.phybasedemo.utils.LogUtils
import com.demo.phy.phybasedemo.utils.TimeUtils
import kotlinx.android.synthetic.main.activity_ipc.*

/**
 * Created by phy on 2019/11/15
 */
class IPCActivity : BaseActivity<IPCView, IPCPresenter>(), IPCView {
    override fun showUid(uid: String) {
        tv_tray.text = uid
    }

    override fun showUserName(string: String) {
        tv_aidl.text = string
    }

    override fun showStringByMessager(string: String) {
        tv_messenger.text = string
    }

    override fun showStringByAidl(string: String) {
    }


    override fun getLayoutId(): Int {
        return R.layout.activity_ipc
    }

    override fun get_Presenter(): IPCPresenter {
        return IPCPresenter(this)
    }

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, IPCActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {

        btn_start.setOnClickListener {
            pPresenter.startConnect()
        }

        btn_messenger.setOnClickListener {
            pPresenter.sendMsgToService(TimeUtils.getDate())
        }

        btn_aidl.setOnClickListener {
            pPresenter.getName()
        }

        btn_tray.setOnClickListener {
            pPresenter.getUid()
        }

        btn_stop.setOnClickListener {
            pPresenter.stopServices()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pPresenter.stopServices()
    }


}