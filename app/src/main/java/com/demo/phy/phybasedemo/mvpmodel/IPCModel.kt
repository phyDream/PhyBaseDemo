package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/11/15
 */
class IPCModel private constructor() {

    companion object {
        private var instance: IPCModel? = null
        fun newInstance(): IPCModel? {
            if (instance == null) {
                synchronized(IPCModel::class.java) {
                    if (instance == null)
                        instance = IPCModel()
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