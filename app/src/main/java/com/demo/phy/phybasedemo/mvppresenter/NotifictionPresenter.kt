package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.NotifictionModel
import com.demo.phy.phybasedemo.mvpview.NotifictionView

/**
 * Created by phy on 2019/11/26
 */
class NotifictionPresenter(view: NotifictionView) : BasePresenter<NotifictionView>(view) {
    var movieModel: NotifictionModel? = null

    init {
        movieModel = NotifictionModel.newInstance()
    }
}