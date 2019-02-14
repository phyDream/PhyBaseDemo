package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.DialogModel
import com.demo.phy.phybasedemo.mvpview.DialogView

class DialogPresenter(view: DialogView) : BasePresenter<DialogView>(view) {
    var movieModel: DialogModel? = null

    init {
        movieModel = DialogModel.newInstance()
    }
}