package com.demo.phy.phybasedemo.utils

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager
import com.demo.phy.phybasedemo.app.MyApplication
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 82353 on 2018/5/2.
 */
object DeviceUtils {
//获取手机IMEI-UserId 6.0以上需要用户同意权限 android.permission.READ_PHONE_STATE。用的是PermissionGen库完成

    @SuppressLint("MissingPermission")
    fun getPhoneId(): String {
        val telephoneManager = MyApplication.mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephoneManager.deviceId
    }

    @SuppressLint("MissingPermission")
    //读取用户手机卡序列号
    fun getSIMNumber(): String {
        val telephoneManager = MyApplication.mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return telephoneManager.simSerialNumber
    }

    /**
     * sim卡是否准备好
     * @return
     */
    fun isSimReady(): Boolean {
        val telephoneManager = MyApplication.mContext.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        try {
            if (telephoneManager.simState == TelephonyManager.SIM_STATE_READY) {
                return true
            }
        } catch (e: Exception) {

        }

        return false
    }


    /**
     * 获取系统当前时间
     * @return 时间格式的字符串
     */
    fun getSystemTime(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val curDate = Date(System.currentTimeMillis())//获取当前时间
        return formatter.format(curDate)
    }
}