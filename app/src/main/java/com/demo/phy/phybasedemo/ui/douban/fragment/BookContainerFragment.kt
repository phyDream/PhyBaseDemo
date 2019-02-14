package com.demo.phy.phybasedemo.ui.douban.fragment


import android.content.Context
import android.support.v4.app.Fragment
import android.view.View
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseFragment
import com.demo.phy.phybasedemo.mvppresenter.BookContainerPresenter
import com.demo.phy.phybasedemo.mvpview.BookContainerView


/**
 * A simple [Fragment] subclass.
 */
class BookContainerFragment : BaseFragment<BookContainerView, BookContainerPresenter>(), BookContainerView {
    override fun bindLayout(): Int {
        return R.layout.fragment_book_container
    }

    override fun initView(view: View?) {

    }

    override fun initData() {

    }

    override fun doBusiness(mContext: Context?) {
    }

    override fun setListener() {
    }

    override fun getPresenter(): BookContainerPresenter {
        return BookContainerPresenter(this)
    }

}
