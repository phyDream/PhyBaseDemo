package com.demo.phy.phybasedemo.service

import android.app.Service
import android.content.Intent
import android.os.*
import com.demo.phy.phybasedemo.utils.ConfigInfo

/**
 * Created by phy on 2019/11/15
 */
class MessagerService : Service() {
    private var cMessenger: Messenger? = null

    companion object {
        const val S_TO_C = "S_TO_C"
        const val C_TO_S = "C_TO_S"
    }


    private val sMessenger = Messenger(object : Handler() {
        override fun handleMessage(msg: Message) {
            cMessenger = msg.replyTo
            val string = msg.data.get(C_TO_S)
            //服务端得到客户端点击的时间，拼接字符串返回给客户端(完成双向通信)
            if(string != null){
                sendMsgToClient(cMessenger!!,"通过Messenger收到来自MessagerService的消息 --  " + string)
            }

            super.handleMessage(msg)
        }
    })

    override fun onBind(p0: Intent?): IBinder {
        ConfigInfo.init(this)
        return sMessenger.getBinder()
    }

    fun sendMsgToClient(cMessenger: Messenger, string: String) {
        try {
            //通知
            val bundle = Bundle()
            bundle.putString(S_TO_C,string)
            val msg = Message.obtain()
            msg.replyTo = cMessenger
            msg.data = bundle
            cMessenger.send(msg)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}