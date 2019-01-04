package com.demo.phy.phybasedemo.ui.main.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.demo.phy.phybasedemo.data.bean.MainListBean

/**
 * Created by phy on 2019/1/4.
 */
/**
 * Created by 蒲弘宇的本地账户 on 2018/5/8.
 */
class MainListAdapter(layoutResId: Int, val context: Context, data: List<MainListBean>) : BaseQuickAdapter<MainListBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: MainListBean?) {


    }

}