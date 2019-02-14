package com.demo.phy.phybasedemo.ui.douban.activity

import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.MovieDetailPresenter
import com.demo.phy.phybasedemo.mvpview.MovieDetailView

class MovieDetailActivity : BaseActivity<MovieDetailView,MovieDetailPresenter>(),MovieDetailView {

    override fun getLayoutId(): Int {
        return R.layout.activity_movie_detail
    }

    override fun get_Presenter(): MovieDetailPresenter {
        return MovieDetailPresenter(this)
    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}
