package com.demo.phy.phybasedemo.mvpview

import com.demo.phy.phybasedemo.base.BaseView

/**
 * Created by phy on 2019/11/15
 */
interface IPCView : BaseView {
    fun showStringByMessager(string: String)
    fun showStringByAidl(string: String)
    fun showUserName(string: String)
    fun showUid(uid: String)
}