package com.demo.phy.phybasedemo.mvpmodel

import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.utils.Constant
import io.reactivex.Observable
import io.reactivex.Observer
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
                        instance = com.demo.phy.phybasedemo.mvpmodel.MainModel()
                }
            }
            return instance
        }
    }

    fun getMainListBean(observer: Observer<ArrayList<MainListBean>>,page:Int){
        Observable.create<ArrayList<MainListBean>> {
            var list = ArrayList<MainListBean>()
            if(page == 1){
                list.add(MainListBean("MVP + Rxjava + retrofit2","2019-1-4",Constant.TAG_MVP,null))
                list.add(MainListBean("弹框","2019-1-30",Constant.TAG_DIALOG,null))
                list.add(MainListBean("进程间通信","2019-11-15",Constant.TAG_IPC,null))
                list.add(MainListBean("fragment","2019-11-21",Constant.TAG_FRAGMENT,null))
                list.add(MainListBean("8.0以上通知适配","2019-11-26",Constant.TAG_NOTIFICATION,null))
                list.add(MainListBean("文件选择器","2019-11-28",Constant.TAG_FILE,null))
                list.add(MainListBean("热更新","2019-11-29",Constant.TAG_HOT_UPDATE,null))
            }
            it.onNext(list)
            it.onComplete()
        }
        .subscribe(observer)
    }

}