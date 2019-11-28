package com.demo.phy.phybasedemo.mvppresenter

import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.mvpmodel.FileModel
import com.demo.phy.phybasedemo.mvpview.FileView

/**
 * Created by phy on 2019/11/28
 */
class FilePresenter(view: FileView) : BasePresenter<FileView>(view) {
    var movieModel: FileModel? = null

    init {
        movieModel = FileModel.newInstance()
    }
}