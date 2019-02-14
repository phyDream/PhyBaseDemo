package com.demo.phy.phybasedemo.mvpmodel

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

    //region 请求网络数据
    //endregion

    //region 请求数据库数据
    //endregion

    //region请求sp数据
    //endregion
}