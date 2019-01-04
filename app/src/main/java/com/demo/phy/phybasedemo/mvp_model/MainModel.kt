package com.demo.phy.phybasedemo.mvp_model

/**
 * Created by phy on 2019/1/2.
 */
class MainModel private constructor() {

    companion object {
        private var instance: MainModel? = null
        fun newInstance(): MainModel? {
            if (instance == null) {
                synchronized(MainModel::class.java) {
                    if (instance == null)
                        instance = com.demo.phy.phybasedemo.mvp_model.MainModel()
                }
            }
            return instance
        }
    }
}