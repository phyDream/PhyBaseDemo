package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.ImageEditorsModel
import com.demo.phy.phybasedemo.mvpview.ImageEditorsView

/**
 * Created by phy on 2019/12/2
 */
class ImageEditorsPresenter(view: ImageEditorsView) : BasePresenter<ImageEditorsView>(view) {
    var movieModel: ImageEditorsModel? = null

    init {
        movieModel = ImageEditorsModel.newInstance()
    }
}