package com.demo.phy.phybasedemo.mvppresenter

import android.app.ActivityManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.widget.Toast
import com.demo.phy.phybasedemo.aidl.binder.BinderPoolImpl.BINDER_CODE_ACCOUNT
import com.demo.phy.phybasedemo.aidl.binder.BinderPoolImpl.BINDER_CODE_BOOK
import com.demo.phy.phybasedemo.app.MyApplication
import com.demo.phy.phybasedemo.base.BasePresenter
import com.demo.phy.phybasedemo.data.bean.IAccount
import com.demo.phy.phybasedemo.data.bean.IBinderPool
import com.demo.phy.phybasedemo.data.bean.IBook
import com.demo.phy.phybasedemo.mvpmodel.IPCModel
import com.demo.phy.phybasedemo.mvpview.IPCView
import com.demo.phy.phybasedemo.service.AIDLService
import com.demo.phy.phybasedemo.service.MessagerService
import com.demo.phy.phybasedemo.utils.ConfigInfo


/**
 * Created by phy on 2019/11/15
 */
class IPCPresenter(view: IPCView) : BasePresenter<IPCView>(view) {
    var ipcModel: IPCModel? = null
    private var sMessenger: Messenger? = null
    private var iBinderPool: IBinderPool? = null
    private var iAccount: IAccount? = null
    private var iBook: IBook? = null

    init {
        ipcModel = IPCModel.newInstance()
    }

    /**
     * 客户端收消息
     */
    private val cMessenger = Messenger(object : Handler() {
        override fun handleMessage(msg: Message) {
            val string = msg.data.getString(MessagerService.S_TO_C)
            view.showStringByMessager(string)
            super.handleMessage(msg)
        }
    })

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, service: IBinder) {
            sMessenger = Messenger(service)
            val msg = Message.obtain()
            //建立通信
            msg.replyTo = cMessenger
            sMessenger!!.send(msg)
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            sMessenger = null
            val context = MyApplication.instance
            try {
                    if (isServiceExisted(context, "com.demo.phy.phybasedemo.service.MessagerService")) {
                    bindService(context)
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        context.startForegroundService(Intent(context, MessagerService::class.java))
                    } else {
                        context.startService(Intent(context, MessagerService::class.java))
                    }
                }
            } catch (e: IllegalStateException) {
                Toast.makeText(context, e.toString(), Toast.LENGTH_LONG)
            }

        }
    }
    var context : Context? = null
    //开启服务进程,建立连接
    fun startConnect() {
        context = MyApplication.instance
        //messager通信
        context?.startService(Intent(context, MessagerService::class.java))
        bindService(context!!)
        //aidl通信
        context?.startService(Intent(context, AIDLService::class.java))
        bindServiceByAidl(context!!)
    }

    fun sendMsgToService(string: String){
        try {
            //通知
            val bundle = Bundle()
            bundle.putString(MessagerService.C_TO_S,string)
            val msg = Message.obtain()
            msg.replyTo = cMessenger
            msg.data = bundle
            sMessenger!!.send(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * 绑定service
     *
     * @param
     */
    private fun bindService(context: Context) {
        val result = context.bindService(Intent(context, MessagerService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    fun isServiceExisted(context: Context, className: String): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val serviceList = activityManager.getRunningServices(Integer.MAX_VALUE)
        if (serviceList.size <= 0) {
            return false
        }
        for (i in serviceList.indices) {
            val serviceInfo = serviceList[i]
            val serviceName = serviceInfo.service
            if (serviceName.className == className) {
                return true
            }
        }
        return false
    }

    //使用aidl跨进程
    private var connAidl: ServiceConnection? = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            iBinderPool = IBinderPool.Stub.asInterface(service)
            try {
                iAccount = IAccount.Stub.asInterface(iBinderPool?.queryBinder(BINDER_CODE_ACCOUNT))
                iBook = IBook.Stub.asInterface(iBinderPool?.queryBinder(BINDER_CODE_BOOK))
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }

        override fun onServiceDisconnected(name: ComponentName) {
        }
    };

    private fun bindServiceByAidl(context: Context) {
        val intent = Intent(context, AIDLService::class.java)
        context.bindService(intent, connAidl, Context.BIND_AUTO_CREATE)
    }

    private fun setName() {
        if (iBook != null) {
            try {
                iBook?.setName("红楼梦")
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

        }
    }

    fun getName(){
        var name = ""
        if (iBook != null) {
            try {
                setName()
                name = iBook?.getName()!!
                view.showUserName(name)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }

        }
    }

    fun getUid(){
        view.showUid(ConfigInfo.getUid())
    }

    fun stopServices(){
        context?.stopService(Intent(context, MessagerService::class.java))
        context?.stopService(Intent(context, AIDLService::class.java))
    }
}