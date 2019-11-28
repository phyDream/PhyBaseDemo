package com.demo.phy.phybasedemo.mvpmodel

/**
 * Created by phy on 2019/11/28
 */
class FileModel private constructor() {

    companion object {
        private var instance: FileModel? = null
        fun newInstance(): FileModel? {
            if (instance == null) {
                synchronized(FileModel::class.java) {
                    if (instance == null)
                        instance = FileModel()
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