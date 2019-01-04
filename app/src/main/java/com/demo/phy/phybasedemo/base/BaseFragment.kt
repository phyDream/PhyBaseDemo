package com.demo.phy.phybasedemo.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.demo.phy.phybasedemo.utils.LogUtils
import me.yokeyword.fragmentation.SupportFragment

/**
 * Created by 82353 on 2018/5/2.
 */
open abstract class BaseFragment<V, T : BasePresenter<V>>: SupportFragment() {
    private var mContextView: View? = null
    protected var mPresenter: T? = null
    //是否处于调试模式
    var DEBUG = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //判断是否使用MVP模式
        mPresenter = getPresenter()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContextView = inflater!!.inflate(bindLayout(), container, false)
        return mContextView
    }


    override fun onLazyInitView(savedInstanceState: Bundle?) {
        super.onLazyInitView(savedInstanceState)
        initView(mContextView)
        initData()
        setListener()
        doBusiness(context)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    /**
     * [绑定布局]
     *
     * @return
     */
    abstract fun bindLayout(): Int

    /**
     * [初始化控件]
     *
     * @param view
     */
    abstract fun initView(view: View?)

    /**
     * 第一次显示时初始化数据
     */
    protected abstract fun initData()

    /**
     * [业务操作]
     *
     * @param mContext
     */
    abstract fun doBusiness(mContext: Context?)

    /** View点击  */
    abstract fun setListener()

    //用于创建Presenter和判断是否使用MVP模式(由子类实现)
    protected abstract fun getPresenter(): T

    //展示log
    protected fun showLog(string: String) {
        if (DEBUG) {
            LogUtils.i(string)
        }
    };

    fun <T : View> `$`(view: View, resId: Int): T {
        return view.findViewById<View>(resId) as T
    }

    /**
     * [防止快速点击]
     *
     * @return
     */
    private fun fastClick(): Boolean {
        var lastClick: Long = 0
        if (System.currentTimeMillis() - lastClick <= 1000) {
            return false
        }
        lastClick = System.currentTimeMillis()
        return true
    }
}