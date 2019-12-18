package com.demo.phy.phybasedemo.ui.main.activity

import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.data.bean.MainListBean
import com.demo.phy.phybasedemo.mvppresenter.MainPresenter
import com.demo.phy.phybasedemo.mvpview.MainView
import com.demo.phy.phybasedemo.ui.dialog.activity.DialogActivity
import com.demo.phy.phybasedemo.ui.douban.activity.MvpDemoMainActivity
import com.demo.phy.phybasedemo.ui.files.activity.FileActivity
import com.demo.phy.phybasedemo.ui.fragment.activity.TabsActivity
import com.demo.phy.phybasedemo.ui.hotupdate.HotUpdateActivity
import com.demo.phy.phybasedemo.ui.imaging.activity.ImageEditorsActivity
import com.demo.phy.phybasedemo.ui.ipc.activity.IPCActivity
import com.demo.phy.phybasedemo.ui.main.adapter.MainListAdapter
import com.demo.phy.phybasedemo.ui.notifiction.activity.NotifictionActivity
import com.demo.phy.phybasedemo.utils.Constant
import com.demo.phy.phybasedemo.utils.StatusBarUtil
import com.demo.phy.phybasedemo.widget.recycler.LoadMoreScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.top_bar_layout.*

/**
 * Created by phy on 2019/1/3.
 * 主界面 - 带分页和查询的list
 */
class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

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
        lifecycle.addObserver(pPresenter)
        center_title.text = getString(R.string.app_name)
        StatusBarUtil.setStatusBarColorAndFontColor(this, R.color.bg_top_bar)
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

    override fun getNaColor():Int{
        return R.color.bg_top_bar
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
                Constant.TAG_IPC ->{
                    IPCActivity.start(this@MainActivity)
                }
                Constant.TAG_FRAGMENT ->{
                    TabsActivity.start(this@MainActivity)
                }
                Constant.TAG_NOTIFICATION ->{
                    NotifictionActivity.start(this@MainActivity)
                }
                Constant.TAG_FILE ->{
                    FileActivity.start(this@MainActivity)
                }
                Constant.TAG_HOT_UPDATE ->{
                    HotUpdateActivity.start(this@MainActivity)
                }
                Constant.TAG_IMAGE_EDITORS ->{
                    ImageEditorsActivity.start(this@MainActivity)
                }
            }
        }
    }

}