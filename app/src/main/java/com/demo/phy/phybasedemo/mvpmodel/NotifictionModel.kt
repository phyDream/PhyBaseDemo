package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/11/26
 */
class NotifictionModel private constructor() {

    companion object {
        private var instance: NotifictionModel? = null
        fun newInstance(): NotifictionModel? {
            if (instance == null) {
                synchronized(NotifictionModel::class.java) {
                    if (instance == null)
                        instance = NotifictionModel()
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