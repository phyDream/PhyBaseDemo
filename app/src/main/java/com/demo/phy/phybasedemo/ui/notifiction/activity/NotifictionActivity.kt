package com.demo.phy.phybasedemo.ui.notifiction.activity

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.widget.RemoteViews
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.NotifictionPresenter
import com.demo.phy.phybasedemo.mvpview.NotifictionView
import kotlinx.android.synthetic.main.activity_notifiction.*
import kotlinx.android.synthetic.main.top_bar_layout.*


/**
 * Created by phy on 2019/11/26
 */
class NotifictionActivity : BaseActivity<NotifictionView, NotifictionPresenter>(), NotifictionView {

    override fun getLayoutId(): Int {
        return R.layout.activity_notifiction
    }

    override fun get_Presenter(): NotifictionPresenter {
        return NotifictionPresenter(this)
    }

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, NotifictionActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
        center_title.text = "通知"
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_showNotification.setOnClickListener {
            showNotification(this,"测试","测试")
        }
        btn_closeNotification.setOnClickListener {
            closeCallNotification(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun showNotification(context: Context, name: String, content: String): Notification {
        val view = RemoteViews(context.packageName, R.layout.notification_call)
        view.setTextViewText(R.id.tv_nikeName, name)
        view.setTextViewText(R.id.tv_content, content)
        val channelId = "call"
        val channelName = "语音消息"
        val importance = NotificationManager.IMPORTANCE_HIGH
        createCallNotificationChannel(channelId, channelName, importance)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val intentPend = PendingIntent.getActivity(MyApplication.instance, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(context, channelId)
                .setCustomContentView(view)
                .setContentTitle(name)
                .setContentText(content)
//                .setContentIntent(intentPend)
                .setOngoing(true)
                .setSmallIcon(com.demo.phy.phybasedemo.R.mipmap.ic_launcher)
        val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setWhen(System.currentTimeMillis())
                    .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher))
                    .build()
        } else {
            builder.build()
        }
        notification.flags = Notification.FLAG_ONGOING_EVENT or Notification.FLAG_ONLY_ALERT_ONCE
        notificationManager.notify(NOTIFICATION_CALL, notification)
        return notification
    }

    fun closeCallNotification(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancel(NOTIFICATION_CALL)
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createCallNotificationChannel(channelId: String, channelName: String, importance: Int) {
        val channel = NotificationChannel(channelId, channelName, importance)
//        val uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.call)
        val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        channel.setSound(uri, null)
        channel.enableVibration(true)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    val NOTIFICATION_CALL = 10000

}