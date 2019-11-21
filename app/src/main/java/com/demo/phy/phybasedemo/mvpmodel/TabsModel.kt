package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/11/21
 */
class TabsModel private constructor() {

    companion object {
        private var instance: TabsModel? = null
        fun newInstance(): TabsModel? {
            if (instance == null) {
                synchronized(TabsModel::class.java) {
                    if (instance == null)
                        instance = TabsModel()
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