package com.demo.phy.phybasedemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.support.annotation.Nullable
import com.demo.phy.phybasedemo.aidl.binder.BinderBookImpl
import com.demo.phy.phybasedemo.aidl.binder.BinderPoolImpl
import com.demo.phy.phybasedemo.utils.ConfigInfo
import com.demo.phy.phybasedemo.utils.LogUtils
import kotlin.concurrent.thread


/**
 * Created by phy on 2019/11/18
 */

class AIDLService : Service() {

    private val binder = BinderPoolImpl()
    private var binderBookImpl = BinderBookImpl()

    @Nullable
    override fun onBind(intent: Intent): IBinder {
        ConfigInfo.init(this)
        ConfigInfo.setUid("1234567890")
        return binder
    }

    override fun onCreate() {
        super.onCreate()
        Thread {
            while(true){
                Thread.sleep(1000)
                LogUtils.e(binder.binderBook.name)
            }
        }.start()
    }
}