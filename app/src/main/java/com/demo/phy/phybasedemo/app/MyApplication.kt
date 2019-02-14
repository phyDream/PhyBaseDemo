package com.demo.phy.phybasedemo.app

import android.app.Activity
import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.os.Process
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory
import me.yokeyword.fragmentation.BuildConfig
import me.yokeyword.fragmentation.Fragmentation
import me.yokeyword.fragmentation.helper.ExceptionHandler
import java.util.*


/**
 * Created by phy on 2018/4/26.
 * 全局MyApplication
 * 1、app引用
 * 2、activity管理
 * 3、SharedPreferencesHelper初始化
 * 4、数据库初始化
 */
class MyApplication:Application(){

    companion object {
        lateinit var mContext:Context
        lateinit var instance: MyApplication;
        //当前前台activity
        var mForegroundActivity: Activity? = null;
        //默认主进程包名
        val REAL_PACKAGE_NAME = "com.demo.phy.phybasedemo"

        /**
         * 记录所有活动的Activity
         */
        val mActivities = LinkedList<Activity>()

        /**
         * 添加一个activity
         */
        fun addElement(activity: Activity){
            mActivities.add(activity)
        }

        /**
         * 删除一个activity
         */
        fun removeElement(activity: Activity){
            mActivities.remove(activity)
        }



        /**
         * 退出APP，关闭所有activity
         */
        fun AppExit(){
            finishAll()
            android.os.Process.killProcess(android.os.Process.myPid())
        }

        /**
         * 关闭所有Activity
         */
        fun finishAll() {
            for (activity in mActivities) {
                activity.finish()
            }
        }

        /**
         * 关闭所有Activity，除了参数传递的Activity
         */
        fun finishAllExceptThis(except: Activity) {
            for (activity in mActivities) {
                if (activity !== except)
                    activity.finish()
            }
        }

        /**
         * 是否有启动的Activity
         */
        fun hasActivity(): Boolean {
            return mActivities.size > 0
        }

        /**
         * 获取当前处于栈顶的activity，无论其是否处于前台
         */
        fun getCurrentActivity(): Activity? {
            return if (mActivities.size > 0) { mActivities[mActivities.size - 1] } else null
        }

        /**
         * 获取当前处于前台的activity
         */
        fun getForegroundActivity(): Activity? {
            return mForegroundActivity
        }

        /**
         * 获得当前进程名
         * @param cxt
         * @param pid
         * @return
         */
        fun getProcessName(cxt: Context, pid: Int): String? {
            val am = cxt.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val runningApps = am.runningAppProcesses ?: return null
            for (procInfo in runningApps) {
                if (procInfo.pid == pid) {
                    return procInfo.processName
                }
            }
            return null
        }
    }

    override fun onCreate() {
        super.onCreate()
        mContext = applicationContext
        instance = this
        //进程名
        val processName = getProcessName(this, Process.myPid())
        if (processName != null) {//进程名不为空
            val defaultProcess = processName == REAL_PACKAGE_NAME
            if (defaultProcess) {//在默认进程才执行（防止后台进程重复调用onCreate()）
                initAppForMainProcess()
            }
        }
    }

    /**
     * APP初始化 - 主进程中运行
     */
    private fun initAppForMainProcess(){
        Fragmentation.builder()
                // 设置 栈视图 模式为 悬浮球模式   SHAKE: 摇一摇唤出   NONE：隐藏
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(BuildConfig.DEBUG)
                // 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                .handleException(object : ExceptionHandler {
                    override fun onException(e: Exception) {
                        // 建议在该回调处上传至我们的Crash监测服务器
                        // 以Bugtags为例子: 手动把捕获到的 Exception 传到 Bugtags 后台。
                        // Bugtags.sendException(e);
                    }
                })
                .install()
    }

    var mWxApi: IWXAPI? = null
    val WEIXIN_APP_ID = "0"
    private fun registerToWX() {
        //第二个参数是指你应用在微信开放平台上的AppID
        mWxApi = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, false);
        // 将该app注册到微信
        mWxApi?.registerApp(WEIXIN_APP_ID);
    }
}