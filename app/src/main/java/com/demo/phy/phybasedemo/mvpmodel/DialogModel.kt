package com.demo.phy.phybasedemo.mvpmodel

import com.demo.phy.phybasedemo.data.bean.PopTypeBean
import io.reactivex.Observable
import io.reactivex.Observer

class DialogModel private constructor() {

    companion object {
        private var instance: DialogModel? = null
        fun newInstance(): DialogModel? {
            if (instance == null) {
                synchronized(DialogModel::class.java) {
                    if (instance == null)
                        instance = DialogModel()
                }
            }
            return instance
        }
    }

    fun getPopTypeBeans(observer: Observer<ArrayList<PopTypeBean>>) {
        Observable.create<ArrayList<PopTypeBean>> {
            var list = ArrayList<PopTypeBean>()

            list.add(PopTypeBean("Loading...", 0))
            list.add(PopTypeBean("下方弹出", 1))
            list.add(PopTypeBean("普通提示", 2))

            it.onNext(list)
            it.onComplete()
        }
                .subscribe(observer)
    }

    //region 请求网络数据
    //endregion

    //region 请求数据库数据
    //endregion

    //region请求sp数据
    //endregion
}