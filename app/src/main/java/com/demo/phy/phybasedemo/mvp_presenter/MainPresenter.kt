package com.demo.phy.phybasedemo.mvp_presenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvp_model.MainModel
import com.demo.phy.phybasedemo.mvp_view.MainView

/**
 * Created by phy on 2019/1/2.
 */
class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {
    var movieModel: MainModel? = null

    init {
        movieModel = MainModel.newInstance()
    }
}