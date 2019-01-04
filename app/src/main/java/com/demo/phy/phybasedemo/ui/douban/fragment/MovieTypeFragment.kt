package com.demo.phy.phybasedemo.ui.douban.fragment


import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.GridLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseFragment
import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import com.demo.phy.phybasedemo.mvp_presenter.MovieTypePresenter
import com.demo.phy.phybasedemo.mvp_view.MovieTypeView
import com.demo.phy.phybasedemo.ui.douban.activity.MovieDetailActivity
import com.demo.phy.phybasedemo.ui.douban.adapter.MovieListAdapter
import com.demo.phy.phybasedemo.utils.LogUtils
import kotlinx.android.synthetic.main.fragment_movie_type.*


/**
 * A simple [Fragment] subclass.
 *
 */
class MovieTypeFragment : BaseFragment<MovieTypeView,MovieTypePresenter>(),MovieTypeView {

    private var mId: String? = null
    private var mAdapter: MovieListAdapter? = null
    private var mMovies = ArrayList<MovieDoubanResponseBean.MovieBeanInDouBan>()
    private var pageCount:Int = 1
    val COLUMNS = 4

    override fun bindLayout(): Int {
        return R.layout.fragment_movie_type
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM = "id"   //参数来区别目标网址(影院热映)

        fun newInstance(id: String): MovieTypeFragment {
            val fragment = MovieTypeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM, id)
            fragment.arguments = args
            return fragment
        }
    }

    override fun initView(view: View?) {
        mAdapter = MovieListAdapter(R.layout.item_movie, context!!,mMovies)
        showContentRecyclerView.layoutManager = GridLayoutManager(activity,COLUMNS, GridLayout.VERTICAL,false) as RecyclerView.LayoutManager?
        showContentRecyclerView.adapter = mAdapter
    }

    override fun initData() {
        mPresenter
        if (arguments != null) {
            mId = arguments!!.getString(ARG_PARAM)
        }
        loaddData()
    }

    fun loaddData(){
        mPresenter?.getMovieList(mId,pageCount)
    }

    override fun complete(list: MutableList<MovieDoubanResponseBean.MovieBeanInDouBan>) {
        mMovies.addAll(list)
        mAdapter?.notifyDataSetChanged()
        mAdapter?.loadMoreComplete()
    }

    override fun doBusiness(mContext: Context?) {
    }

    override fun setListener() {
        mAdapter?.setOnLoadMoreListener(object :BaseQuickAdapter.RequestLoadMoreListener{
            override fun onLoadMoreRequested() {
                pageCount++
                LogUtils.i("~pageCount~"+pageCount)
                loaddData()
            }

        })

        mAdapter?.setOnItemClickListener { adapter, view, position ->
            var intent = Intent(activity,MovieDetailActivity::class.java)
            startActivity(intent)
        }
    }

    override fun getPresenter(): MovieTypePresenter {
        return MovieTypePresenter(this)
    }

}
