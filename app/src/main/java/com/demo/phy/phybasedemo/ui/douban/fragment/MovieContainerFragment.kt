package com.demo.phy.phybasedemo.ui.douban.fragment

import android.content.Context
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.GestureDetector
import android.view.View
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseFragment
import com.demo.phy.phybasedemo.mvp_presenter.MovieContainerPresenter
import com.demo.phy.phybasedemo.mvp_view.MovieContainerView
import com.demo.phy.phybasedemo.ui.douban.adapter.CommonPagerAdapter
import com.demo.phy.phybasedemo.utils.Constant
import kotlinx.android.synthetic.main.fragment_movie_container.*

/**
 * Created by 82353 on 2018/5/2.
 */
class MovieContainerFragment : BaseFragment<MovieContainerView,MovieContainerPresenter>(),MovieContainerView{

    protected var tabs = arrayListOf<String>()
    protected var fragments = arrayListOf<Fragment>()
    private var mDetector : GestureDetector? = null
    private var mAdapter : CommonPagerAdapter? = null

    override fun bindLayout(): Int {
        return R.layout.fragment_movie_container
    }

    override fun initView(view: View?) {
        //初始化内容fragment
        setFragments()
        //初始化tabs
        tabs = mPresenter!!.getMovieTabs()
        mAdapter = CommonPagerAdapter(childFragmentManager,fragments,tabs)
        movieViewPager.adapter = mAdapter
        movieViewPager.offscreenPageLimit = 3     //缓存三个页面的内容
        movieTabLayout.setupWithViewPager(movieViewPager)
        setUpTab()
    }

    //设置fragments
    fun setFragments(){
        fragments.add(MovieTypeFragment.newInstance(Constant.THEATER))
        fragments.add(MovieTypeFragment.newInstance(Constant.COMING))
        fragments.add(MovieTypeFragment.newInstance(Constant.Top250))
        fragments.add(MovieTypeFragment.newInstance(Constant.THEATER))
    }

    //一页里面只显示四个item，如果item的数量大于四，那么更改显示模式,现在还没有大于四个的tab
    fun setUpTab(){
        if (tabs.size > Constant.TABDIVIDER){
            movieTabLayout!!.tabMode = TabLayout.MODE_SCROLLABLE
        }else{
            movieTabLayout!!.tabMode = TabLayout.MODE_FIXED
            movieTabLayout!!.tabGravity = TabLayout.GRAVITY_FILL
        }
    }

    override fun initData() {

    }

    override fun doBusiness(mContext: Context?) {
    }

    override fun setListener() {
    }

    override fun getPresenter(): MovieContainerPresenter {
        return MovieContainerPresenter(this)
    }

}