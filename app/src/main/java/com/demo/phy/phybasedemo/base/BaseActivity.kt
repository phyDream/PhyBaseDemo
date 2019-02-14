package com.demo.phy.phybasedemo.base

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.CheckResult
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
        StatusBarUtil.setColor(this, resources.getColor(R.color.bg_top_bar), 0)
        pPresenter = get_Presenter();
        initView()
        initListener()
        initData()

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
}