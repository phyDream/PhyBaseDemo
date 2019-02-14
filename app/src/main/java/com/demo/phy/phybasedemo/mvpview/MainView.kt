package com.demo.phy.phybasedemo.mvpview

import com.demo.phy.phybasedemo.base.BaseView
import com.demo.phy.phybasedemo.data.bean.MainListBean

/**
 * Created by 82353 on 2018/4/26.
 */
interface MainView:BaseView{
    fun complete(list: List<MainListBean>)
}