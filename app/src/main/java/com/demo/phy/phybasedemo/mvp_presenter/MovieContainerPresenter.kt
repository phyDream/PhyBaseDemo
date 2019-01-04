package com.demo.phy.phybasedemo.mvp_presenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvp_model.MovieModel
import com.demo.phy.phybasedemo.mvp_view.MovieContainerView

/**
 * Created by 82353 on 2018/5/2.
 */
class MovieContainerPresenter(view: MovieContainerView) : BasePresenter<MovieContainerView>(view) {
    var movieModel: MovieModel? = null
    init {
        movieModel = MovieModel.newInstance()
    }

    fun getMovieTabs():ArrayList<String>{
        val ret = ArrayList<String>()
        ret += arrayListOf("正在热播","即将上映","Top250", "本地视频")
        return ret
    }
}