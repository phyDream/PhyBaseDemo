package com.demo.phy.phybasedemo.ui.dialog.adapter

import android.content.Context
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.data.bean.PopTypeBean
import com.demo.phy.phybasedemo.utils.ImageLoadUtil

/**
 * Created by 蒲弘宇的本地账户 on 2018/5/8.
 */
class DialogTypeAdapter(layoutResId: Int, val context: Context, data: List<PopTypeBean>) : BaseQuickAdapter<PopTypeBean, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: PopTypeBean?) {
        helper?.setText(R.id.tv_name,item?.name)
    }

}