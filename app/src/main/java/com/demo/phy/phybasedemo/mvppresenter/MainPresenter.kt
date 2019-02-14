package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.mvpmodel.MainModel
import com.demo.phy.phybasedemo.mvpview.MainView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * Created by phy on 2019/1/2.
 */
class MainPresenter(view: MainView) : BasePresenter<MainView>(view) {
    var movieModel: MainModel? = null

    init {
        movieModel = MainModel.newInstance()
    }


    //获取数据
    fun getData(page: Int){
        var observer = object : Observer<ArrayList<MainListBean>> {
            override fun onNext(t: ArrayList<MainListBean>) {
                view.complete(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

        }

        movieModel?.getMainListBean(observer,page)
    }
}