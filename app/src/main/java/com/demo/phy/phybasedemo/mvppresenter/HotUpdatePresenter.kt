package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.HotUpdateModel
import com.demo.phy.phybasedemo.mvpview.HotUpdateView

/**
 * Created by phy on 2019/11/29
 */
class HotUpdatePresenter(view: HotUpdateView) : BasePresenter<HotUpdateView>(view) {
    var movieModel: HotUpdateModel? = null

    init {
        movieModel = HotUpdateModel.newInstance()
    }
}