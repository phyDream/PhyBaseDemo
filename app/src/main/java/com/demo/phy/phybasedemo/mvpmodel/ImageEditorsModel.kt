package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/12/2
 */
class ImageEditorsModel private constructor() {

    companion object {
        private var instance: ImageEditorsModel? = null
        fun newInstance(): ImageEditorsModel? {
            if (instance == null) {
                synchronized(ImageEditorsModel::class.java) {
                    if (instance == null)
                        instance = ImageEditorsModel()
                }
            }
            return instance
        }
    }

    //region 请求网络数据
    //endregion

    //region 请求数据库数据
    //endregion

    //region请求sp数据
    //endregion
}