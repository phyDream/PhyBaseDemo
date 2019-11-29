package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/11/29
 */
class HotUpdateModel private constructor() {

    companion object {
        private var instance: HotUpdateModel? = null
        fun newInstance(): HotUpdateModel? {
            if (instance == null) {
                synchronized(HotUpdateModel::class.java) {
                    if (instance == null)
                        instance = HotUpdateModel()
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