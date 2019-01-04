package com.demo.phy.phybasedemo.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.demo.phy.phybasedemo.app.MyApplication

/**
 * Created by 82353 on 2018/5/2.
 */
object NetWorkUtils {
    /**
     * 网络请求成功标记码
     */
    val NETWORK_SUCCEED = 0

    /**
     * 有网但是请求服务器失败时。
     */
    val CLIENT_FAILED = -2
    /**
     * 无网络标志
     */
    val NETWORK_FAILED = -1


    private val context = MyApplication.mContext;

    // 网络状态，连接wifi，cmnet是直连互联网的，cmwap是需要代理，noneNet是无连接的
    // 一速度来说：wifi > cmnet >cmwap > noneNet
    enum class NetType {
        wifi, CMNET, CMWAP, noneNet
    }

    /**
     * 网络是否可用
     *
     * @return
     */
    fun isNetworkAvailable(): Boolean {
        // 获取网络manager
        val mgr = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val info = mgr.allNetworkInfo

        // 遍历所有可以连接的网络
        if (info != null) {
            for (i in info.indices) {
                if (info[i].state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
        }
        return false
    }


    /**
     * 判断是否有网络连接
     *
     * @return
     */
    fun isNetworkConnected(): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 判断WIFI网络是否可用
     *
     * @return
     */
    fun isWifiConnected(): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetInfo = mConnectivityManager.activeNetworkInfo
            if (activeNetInfo != null && activeNetInfo.type == ConnectivityManager.TYPE_WIFI) {
                return true
            }
        }
        return false


    }

    /**
     * 判断MOBILE网络是否可用
     *
     * @return
     */
    fun isMobileConnected(): Boolean {
        if (context != null) {
            val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mMobileNetworkInfo = mConnectivityManager
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
            if (mMobileNetworkInfo != null) {
                return mMobileNetworkInfo.isAvailable
            }
        }
        return false
    }

    /**
     * 获取当前网络连接的类型信息
     *
     * @return
     */
    fun getConnectedType(): Int {
        if (context != null) {
            val mConnectivityManager = context!!
                    .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val mNetworkInfo = mConnectivityManager.activeNetworkInfo
            if (mNetworkInfo != null && mNetworkInfo.isAvailable) {
                return mNetworkInfo.type
            }
        }
        return -1
    }

    /**
     *
     * 获取当前的网络状态 -1：没有网络 1：WIFI网络2：wap 网络3：net网络
     *
     *
     * @return
     */
    fun getAPNType(): NetType {
        val connMgr = context!!
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo ?: return NetType.noneNet
        val nType = networkInfo.type

        if (nType == ConnectivityManager.TYPE_MOBILE) {
            return if (networkInfo.extraInfo.toLowerCase() == NetType.CMNET.toString()) {
                NetType.CMNET
            } else {
                NetType.CMWAP
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            return NetType.wifi
        }
        return NetType.noneNet

    }

}