package com.demo.phy.phybasedemo.mvp_view

import com.demo.phy.phybasedemo.base.BaseView
import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean

interface MovieTypeView:BaseView{
    fun complete(list: MutableList<MovieDoubanResponseBean.MovieBeanInDouBan>)
}
