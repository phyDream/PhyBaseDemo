package com.demo.phy.phybasedemo.ui.douban.fragment

import android.content.Context
import android.view.View
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseFragment
import com.demo.phy.phybasedemo.mvppresenter.MusicContainerPresenter
import com.demo.phy.phybasedemo.mvpview.MusicContainerView

/**
 * Created by 82353 on 2018/5/2.
 */
class MusicContainerFragment : BaseFragment<MusicContainerView,MusicContainerPresenter>(),MusicContainerView{
    override fun bindLayout(): Int {
        return R.layout.fragment_music_container
    }

    override fun initView(view: View?) {
    }

    override fun initData() {
    }

    override fun doBusiness(mContext: Context?) {
    }

    override fun setListener() {
    }

    override fun getPresenter(): MusicContainerPresenter {
        return MusicContainerPresenter(this)
    }

}