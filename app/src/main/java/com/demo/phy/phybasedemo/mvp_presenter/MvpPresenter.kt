package com.demo.phy.phybasedemo.mvp_presenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvp_model.MovieModel
import com.demo.phy.phybasedemo.mvp_view.MainView

/**
 * Created by 82353 on 2018/4/26.
 */
class MvpPresenter(view: MainView) : BasePresenter<MainView>(view) {
    var movieModel:MovieModel? = null
    init {
        movieModel = MovieModel.newInstance()
    }
}