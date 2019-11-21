package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.TabsModel
import com.demo.phy.phybasedemo.mvpview.TabsView

/**
 * Created by phy on 2019/11/21
 */
class TabsPresenter(view: TabsView) : BasePresenter<TabsView>(view) {
    var movieModel: TabsModel? = null

    init {
        movieModel = TabsModel.newInstance()
    }
}