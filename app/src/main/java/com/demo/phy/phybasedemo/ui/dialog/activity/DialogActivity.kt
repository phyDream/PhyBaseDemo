package com.demo.phy.phybasedemo.ui.dialog.activity

import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.data.bean.PopTypeBean
import com.demo.phy.phybasedemo.mvppresenter.DialogPresenter
import com.demo.phy.phybasedemo.mvpview.DialogView
import com.demo.phy.phybasedemo.ui.dialog.adapter.DialogTypeAdapter
import com.demo.phy.phybasedemo.ui.dialog.view.BottomPop
import com.demo.phy.phybasedemo.ui.dialog.view.NormalTipDialog
import com.demo.phy.phybasedemo.ui.dialog.view.OptionMenuPop
import com.demo.phy.phybasedemo.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_dialog.*
import kotlinx.android.synthetic.main.top_bar_layout.*
import java.util.*

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

    lateinit var mOptionMenuPop : OptionMenuPop
    lateinit var mBottomPop : BottomPop
    lateinit var mNormalTipDialog : NormalTipDialog
    lateinit var mDialogTypeAdapter : DialogTypeAdapter
    var data  = ArrayList<PopTypeBean>()

    override fun initView() {
        left_back.visibility = View.VISIBLE
        center_title.visibility = View.VISIBLE
        center_title.text = "弹框"
        right_icon.visibility = View.VISIBLE

        rv_dialogType.layoutManager = LinearLayoutManager(this)
        mDialogTypeAdapter = DialogTypeAdapter(R.layout.item_base,this,data)
        rv_dialogType.adapter = mDialogTypeAdapter

        mOptionMenuPop = OptionMenuPop()
        mOptionMenuPop.init(this@DialogActivity)

        mBottomPop = BottomPop()
        mBottomPop.init(this@DialogActivity)

        mNormalTipDialog = NormalTipDialog(this)
    }

    override fun initData() {
        loadData()
    }

    override fun initListener() {
        left_back.setOnClickListener {
            onBackPressed()
        }

        right_icon.setOnClickListener {
            mOptionMenuPop.show(right_icon)
        }

        mDialogTypeAdapter?.setOnItemClickListener{adapter,view,position->
            var item = data.get(position)
            when(item.id){
                0 ->{
                    showLoadingDialog()
                    Timer().schedule(object : TimerTask() {
                        override fun run() {
                           dismissLoadingDialog()
                        }
                    },3000)
                }
                1 ->{
                    mBottomPop.show(rv_dialogType)
                }
                2 ->{
                    mNormalTipDialog.show("我的提示",object : NormalTipDialog.OnClickListenner{
                        override fun onSure(position: Int) {
                            ToastUtils.showToast(this@DialogActivity,"确定")
                        }

                    })
                }
            }
        }
    }

    fun loadData(){
        pPresenter.getData()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun complete(list: List<PopTypeBean>) {
        data.addAll(list)
        mDialogTypeAdapter.notifyDataSetChanged()
    }


}