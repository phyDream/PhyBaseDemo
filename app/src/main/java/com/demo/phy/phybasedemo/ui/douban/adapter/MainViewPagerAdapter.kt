package com.demo.phy.phybasedemo.ui.douban.adapter


import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by 蒲弘宇的本地账户 on 2018/5/7.
 */
class MainViewPagerAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm){

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return fragmentList.get(position)
    }
}