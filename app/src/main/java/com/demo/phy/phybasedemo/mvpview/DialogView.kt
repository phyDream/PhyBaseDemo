package com.demo.phy.phybasedemo.mvpview

import com.demo.phy.phybasedemo.base.BaseView
import com.demo.phy.phybasedemo.data.bean.PopTypeBean

interface DialogView : BaseView {
    fun complete(list: List<PopTypeBean>)
}