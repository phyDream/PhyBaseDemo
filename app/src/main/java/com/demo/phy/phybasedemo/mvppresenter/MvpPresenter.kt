package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.MovieModel
import com.demo.phy.phybasedemo.mvpview.MvpDemoMainView

/**
 * Created by 82353 on 2018/4/26.
 */
class MvpPresenter(view: MvpDemoMainView) : BasePresenter<MvpDemoMainView>(view) {
    var movieModel:MovieModel? = null
    init {
        movieModel = MovieModel.newInstance()
    }
}