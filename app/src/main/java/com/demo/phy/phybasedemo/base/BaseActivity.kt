package com.demo.phy.phybasedemo.base

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.CheckResult
import android.support.v4.graphics.ColorUtils
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.app.MyApplication
import com.demo.phy.phybasedemo.utils.StatusBarUtil
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import me.yokeyword.fragmentation.SupportActivity

/**
 * Created by 82353 on 2018/4/26.
 * activity抽象基类
 */

open abstract class BaseActivity<V:BaseView,T:BasePresenter<V>>: SupportActivity(),LifecycleProvider<ActivityEvent>{

    lateinit var pPresenter: T

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.CREATE)
        MyApplication.mActivities.add(this)//存放所有activity的引用
        setContentView(getLayoutId())
        initStatusBar()
        pPresenter = get_Presenter();
        initView()
        initListener()
        initData()

    }

    private fun initStatusBar() {
        //白状态栏、黑字
//        if (true) {
//            StatusBarUtil.WhiteStatusBarAndBlackFont(this)
//            return
//        }
        if (isImageTheme()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.statusBarColor = Color.TRANSPARENT
                // window.setNavigationBarColor(Color.TRANSPARENT);
            }
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = window
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                window.statusBarColor = getNaColor()
                // window.setNavigationBarColor(Color.TRANSPARENT);
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isLightColor(getNaColor())) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
    }

    open fun isImageTheme(): Boolean {
        return false
    }

    open fun getNaColor(): Int {
        return resources.getColor(R.color.title_bg)
    }

    /**
     * 判断颜色是不是亮色
     *
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    protected fun isLightColor(color: Int): Boolean {
        return ColorUtils.calculateLuminance(color) >= 0.5
    }

    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()

    @CheckResult
    override fun lifecycle(): Observable<ActivityEvent> {
        return lifecycleSubject.hide()
    }

    @CheckResult
    override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject)
    }



    @CallSuper
    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
        MyApplication.mForegroundActivity = this
    }

    @CallSuper
    override fun onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
        MyApplication.mForegroundActivity = null
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }


    /**
     * [得到布局ID]
     */

    protected abstract fun getLayoutId():Int

    /**
     *
     */
    protected abstract fun get_Presenter(): T

    /**
     * [初始化控件]
     */
    protected abstract fun initView()

    /**
     * [初始化数据]
     */
    protected abstract fun initData()


    /**
     * [设置监听]
     */
    protected abstract fun initListener();

    private var alertDialog: AlertDialog? = null

    fun showLoadingDialog() {
        showLoadingDialog(true, false, false)
    }

    fun showLoadingDialog(cancelable: Boolean, canNotback: Boolean, canceledOnTouchOutside: Boolean) {
        if (alertDialog == null) {
            alertDialog = AlertDialog.Builder(this).create()
            alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable())
            alertDialog!!.setCancelable(cancelable)
            alertDialog!!.setCanceledOnTouchOutside(false)
            alertDialog!!.setOnKeyListener { dialog, keyCode, event -> if (keyCode == KeyEvent.KEYCODE_SEARCH || keyCode == KeyEvent.KEYCODE_BACK) canNotback else false }
            alertDialog!!.setCanceledOnTouchOutside(canceledOnTouchOutside)
        }
        alertDialog!!.show()
        alertDialog!!.setContentView(R.layout.loading_alert)
    }

    fun dismissLoadingDialog() {

        runOnUiThread {
            if (null != alertDialog && alertDialog!!.isShowing) {
                alertDialog!!.dismiss()
            }
        }

    }
}