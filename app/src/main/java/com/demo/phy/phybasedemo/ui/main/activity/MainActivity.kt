package com.demo.phy.phybasedemo.ui.main.activity

import android.support.v7.widget.LinearLayoutManager
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.mvppresenter.MainPresenter
import com.demo.phy.phybasedemo.mvpview.MainView
import com.demo.phy.phybasedemo.ui.dialog.activity.DialogActivity
import com.demo.phy.phybasedemo.ui.douban.activity.MvpDemoMainActivity
import com.demo.phy.phybasedemo.ui.main.adapter.MainListAdapter
import com.demo.phy.phybasedemo.utils.Constant
import com.demo.phy.phybasedemo.widget.recycler.LoadMoreScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_bar_layout.*

/**
 * Created by phy on 2019/1/3.
 * 主界面 - 带分页和查询的list
 */
class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView{

    //region 变量
    private var mAdapter: MainListAdapter? = null
    private var mData = ArrayList<MainListBean>()
    private var mPage:Int = 1
    private val PAGE_COUNT:Int = 10
    //endregion

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun get_Presenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun initView() {
        center_title.text = getString(R.string.app_name)

        mAdapter = MainListAdapter(mData)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.setOnScrollListener(LoadMoreScrollListener(mRecyclerView))
        mRecyclerView.adapter = mAdapter

    }

    override fun initData() {
        loadData()
    }

    fun loadData(){
        pPresenter.getData(mPage)
    }

    override fun complete(list: List<MainListBean>) {
        mData.addAll(list)
        mAdapter?.notifyDataSetChanged()

    }

    override fun initListener() {
        mAdapter?.setLoadMoreListener(object : MainListAdapter.LoadMoreListener{
            override fun loadMoreData() {
                loadData()
            }

        },PAGE_COUNT)

        mAdapter?.setOnItemClickListener { item, position ->

            when(item.tag){
                Constant.TAG_MVP ->{
                    MvpDemoMainActivity.start(this@MainActivity)
                }
                Constant.TAG_DIALOG ->{
                    DialogActivity.start(this@MainActivity)
                }
            }
        }
    }

}