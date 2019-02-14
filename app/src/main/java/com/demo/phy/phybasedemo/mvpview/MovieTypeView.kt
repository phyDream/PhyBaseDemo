package com.demo.phy.phybasedemo.mvpview

import com.demo.phy.phybasedemo.base.BaseView
import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean

interface MovieTypeView:BaseView{
    fun complete(list: MutableList<MovieDoubanResponseBean.MovieBeanInDouBan>)
}
