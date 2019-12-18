package com.demo.phy.phybasedemo.ui.douban.adapter


import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * Created by 蒲弘宇的本地账户 on 2018/5/7.
 */
class MainViewPagerAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentStatePagerAdapter(fm){

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }
}