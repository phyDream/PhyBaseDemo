package com.demo.phy.phybasedemo.ui.douban.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.widget.RadioButton
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.MvpPresenter
import com.demo.phy.phybasedemo.mvpview.MvpDemoMainView
import com.demo.phy.phybasedemo.ui.douban.adapter.MainViewPagerAdapter
import com.demo.phy.phybasedemo.ui.douban.fragment.BookContainerFragment
import com.demo.phy.phybasedemo.ui.douban.fragment.MovieContainerFragment
import com.demo.phy.phybasedemo.ui.douban.fragment.MusicContainerFragment
import com.demo.phy.phybasedemo.utils.Constant
import com.demo.phy.phybasedemo.utils.StatusBarUtil
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_mvp_main.*
import me.yokeyword.fragmentation.SupportFragment
import java.util.concurrent.TimeUnit

class MvpDemoMainActivity:BaseActivity<MvpDemoMainView, MvpPresenter>(), MvpDemoMainView, ViewPager.OnPageChangeListener{

    private var tabs = arrayListOf<RadioButton>();
    private var currIndex = 1//当前位置
    private val mFragments = arrayListOf<SupportFragment>()
    private var disposable : Disposable? = null

    companion object {
        fun start(context: Context){
            var intent = Intent(context,MvpDemoMainActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_mvp_main
    }

    override fun get_Presenter(): MvpPresenter {
        return MvpPresenter(this);
    }

    override fun initView() {
        StatusBarUtil.setStatusBarColorAndFontColor(this, R.color.bg_top_bar)
        //获得顶部栏
        tabs.add(goToMusic)
        tabs.add(goToMovie)
        tabs.add(goToBook)

        //设置ViewPager
        mFragments.add(MusicContainerFragment())
        mFragments.add(MovieContainerFragment())
        mFragments.add(BookContainerFragment())
        val viewPagerAdapter = MainViewPagerAdapter(supportFragmentManager, mFragments)
        mainContentViewPager?.adapter = viewPagerAdapter
        mainContentViewPager?.addOnPageChangeListener(this)
        mainContentViewPager.setCurrentItem(Constant.MOVIE)//默认显示电影

    }

    override fun getNaColor():Int{
        return R.color.bg_top_bar
    }

    @SuppressLint("CheckResult")
    override fun initListener() {
        RxView.clicks(goToMusic)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(com.trello.rxlifecycle2.android.ActivityEvent.DESTROY))//绑定生命周期的方式，来解决内存泄漏的问题。
                .subscribe {
                    mainContentViewPager.setCurrentItem(Constant.MUSIC)
                }
        RxView.clicks(goToMovie)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(com.trello.rxlifecycle2.android.ActivityEvent.DESTROY))//绑定生命周期的方式，来解决内存泄漏的问题。
                .subscribe {
                    mainContentViewPager.setCurrentItem(Constant.MOVIE)
                }
        RxView.clicks(goToBook)
                .throttleFirst(1, TimeUnit.SECONDS)
                .compose(this.bindUntilEvent(com.trello.rxlifecycle2.android.ActivityEvent.DESTROY))//绑定生命周期的方式，来解决内存泄漏的问题。
                .subscribe {
                    mainContentViewPager.setCurrentItem(Constant.BOOK)
                }

        var list = ArrayList<Int>()
        for (i in 1..100){
            list.add(i)
        }
    }

    override fun initData() {
        disposable = Observable.timer(5,TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .compose(this.bindUntilEvent(com.trello.rxlifecycle2.android.ActivityEvent.DESTROY))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                }
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        currIndex = position
        changeIcons(position)//切换页的时候，设置顶部图片切换
    }

    fun changeIcons(position: Int) {
        tabs[position].isChecked = true
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable?.dispose()
    }
}
