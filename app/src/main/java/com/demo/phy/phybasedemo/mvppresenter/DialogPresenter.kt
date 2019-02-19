package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.data.bean.PopTypeBean
import com.demo.phy.phybasedemo.mvpmodel.DialogModel
import com.demo.phy.phybasedemo.mvpview.DialogView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class DialogPresenter(view: DialogView) : BasePresenter<DialogView>(view) {
    var mDialogModel: DialogModel? = null

    init {
        mDialogModel = DialogModel.newInstance()
    }

    //获取数据
    fun getData(){
        var observer = object : Observer<ArrayList<PopTypeBean>> {
            override fun onNext(t: ArrayList<PopTypeBean>) {
                view.complete(t)
            }

            override fun onError(e: Throwable) {
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onComplete() {
            }

        }

        mDialogModel?.getPopTypeBeans(observer)
    }
}