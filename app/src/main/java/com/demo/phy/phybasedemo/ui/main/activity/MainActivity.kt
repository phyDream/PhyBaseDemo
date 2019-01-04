package com.demo.phy.phybasedemo.ui.main.activity

import android.support.v7.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.mvp_presenter.MainPresenter
import com.demo.phy.phybasedemo.mvp_view.MainView
import com.demo.phy.phybasedemo.ui.main.adapter.MainListAdapter
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
    private var mPageCount:Int = 1
    //endregion

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun get_Presenter(): MainPresenter {
        return MainPresenter(this)
    }

    override fun initView() {
        center_title.text = getString(R.string.app_name)

        mAdapter = MainListAdapter(R.layout.item_base,this,mData)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        loadData()
    }

    fun loadData(){

    }

    override fun setLinstener() {
        mAdapter?.setOnLoadMoreListener(object : BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                mPageCount++
                loadData()
            }

        })

        mAdapter?.setOnItemClickListener { adapter, view, position ->

        }
    }

}