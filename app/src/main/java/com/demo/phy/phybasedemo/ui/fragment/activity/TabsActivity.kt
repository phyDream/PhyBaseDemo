package com.demo.phy.phybasedemo.ui.fragment.activity

import android.content.Context
import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import android.widget.CompoundButton
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.TabsPresenter
import com.demo.phy.phybasedemo.mvpview.TabsView
import com.demo.phy.phybasedemo.ui.fragment.fragment.FourFragment
import com.demo.phy.phybasedemo.ui.fragment.fragment.OneFragment
import com.demo.phy.phybasedemo.ui.fragment.fragment.ThreeFragment
import com.demo.phy.phybasedemo.ui.fragment.fragment.TwoFragment
import com.demo.phy.phybasedemo.utils.LogUtils
import com.demo.phy.phybasedemo.utils.StatusBarUtil
import kotlinx.android.synthetic.main.activity_tabs.*

/**
 * Created by phy on 2019/11/21
 */
class TabsActivity : BaseActivity<TabsView, TabsPresenter>(), TabsView, CompoundButton.OnCheckedChangeListener {
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            val id = buttonView?.id
            when (id) {
                R.id.rd_main_im -> {
                    setIndexSelected(0)
                }
                R.id.rd_main_mail_list -> {
                    setIndexSelected(1)
                }
                R.id.rd_main_find -> {
                    setIndexSelected(2)
                }
                R.id.rd_main_mine -> {
                    setIndexSelected(3)
                }
            }
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_tabs
    }

    override fun get_Presenter(): TabsPresenter {
        return TabsPresenter(this)
    }

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, TabsActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
        initFragment()
    }

    override fun initData() {

    }

    override fun initListener() {

    }

    private fun initFragment() {
        mOneFragment = OneFragment()
        mTwoFragment = TwoFragment()
        mThreeFragment = ThreeFragment()
        mFourFragment = FourFragment()
        mFragments.add(mOneFragment!!)
        mFragments.add(mTwoFragment!!)
        mFragments.add(mThreeFragment!!)
        mFragments.add(mFourFragment!!)
        mTransaction = supportFragmentManager.beginTransaction()
        mTransaction?.add(R.id.fragment_container, mOneFragment!!, "0")?.commit()
        mIndex = 0

        rd_main_im.setOnCheckedChangeListener(this)
        rd_main_mail_list.setOnCheckedChangeListener(this)
        rd_main_find.setOnCheckedChangeListener(this)
        rd_main_mine.setOnCheckedChangeListener(this)
    }

    private fun setIndexSelected(index: Int) {
        try {
            if (mIndex == index) {
                return
            }
            val fragmentManager = supportFragmentManager
            val ft = fragmentManager.beginTransaction()
            ft.hide(mFragments[mIndex])
            if (!mFragments[index].isAdded) {
                ft.add(R.id.fragment_container, mFragments[index], index.toString()).show(mFragments[index])
            } else {
                ft.show(mFragments[index])
            }
            ft.commit()
            mIndex = index
        } catch (e: Exception) {
            LogUtils.e(e.toString())
        }


    }

    private var mIndex = 0
    private var mFragments = ArrayList<Fragment>()
    private var mTransaction: FragmentTransaction? = null
    private var mOneFragment: OneFragment? = null
    private var mTwoFragment: TwoFragment? = null
    private var mThreeFragment: ThreeFragment? = null
    private var mFourFragment: FourFragment? = null

}