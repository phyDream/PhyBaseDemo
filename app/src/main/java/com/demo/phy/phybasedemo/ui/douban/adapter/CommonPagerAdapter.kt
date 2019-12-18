package com.demo.phy.phybasedemo.ui.douban.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class CommonPagerAdapter(fm: FragmentManager, private val fragments:List<Fragment>, private val tabs:List<String>): FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return fragments.get(position)
    }

    override fun getCount(): Int {
        return fragments.size
    }

    //此方法用来显示tab上的名字
    override fun getPageTitle(position: Int): CharSequence {
        return tabs[position % tabs.size]
    }

}