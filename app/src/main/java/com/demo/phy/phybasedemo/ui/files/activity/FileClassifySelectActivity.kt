package com.demo.phy.phybasedemo.ui.files.activity

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.data.bean.MyFile
import com.demo.phy.phybasedemo.mvppresenter.FilePresenter
import com.demo.phy.phybasedemo.mvpview.FileView
import com.demo.phy.phybasedemo.ui.files.adpter.ViewPagerFileAdapter
import com.demo.phy.phybasedemo.utils.Constant
import com.demo.phy.phybasedemo.utils.StatusBarUtil
import com.demo.phy.phybasedemo.utils.ToastUtils
import com.demo.phy.phybasedemo.utils.TransformUtil
import com.outim.mechat.ui.fragment.FileTypeFragment
import kotlinx.android.synthetic.main.activity_file_classify_select.*
import kotlinx.android.synthetic.main.top_bar_layout.*
import java.lang.reflect.Field
import java.util.*

/**
 * Created by phy on 2019/11/27
 */
class FileClassifySelectActivity : BaseActivity<FileView, FilePresenter>(), FileView{
    override fun get_Presenter(): FilePresenter {
        return FilePresenter(this)
    }

    override fun initData() {
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_file_classify_select
    }

    private val fragments = ArrayList<FileTypeFragment>()
    private val types = ArrayList<String>()
    private var AdapterForViewPagerFile: ViewPagerFileAdapter? = null
    private val filesByPitchOn = ArrayList<MyFile>()//选中的文件数据集合
    private var paths = ArrayList<String>()//选中的文件路径集合
    private var maxNum = 9

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: BaseActivity<FileView,FilePresenter>,num: Int) {
            var intent = Intent(context, FileClassifySelectActivity::class.java)
            intent.putExtra(KEY_STR1,num)
            context.startActivityForResult(intent,FileActivity.REQUEST_CODE_CHOOSE_FILE)
        }

    }

    override fun initView() {
        StatusBarUtil.setStatusBarColorAndFontColor(this, R.color.bg_top_bar)
        maxNum = intent.getIntExtra(KEY_STR1,9)
        left_back.text = "分类选择"
        left_back.visibility = View.VISIBLE
        right_icon.visibility = View.GONE
        initViewPager()
    }

    override fun getNaColor():Int{
        return R.color.bg_top_bar
    }

    override fun initListener() {
        lin_bottom.setOnClickListener {
            if (filesByPitchOn.size <= 0) {
                ToastUtils.showToast(this,"还没选择")
            } else if(filesByPitchOn.size > maxNum){
                ToastUtils.showToast(this,"超出限制")
            }else{
                paths = filesByPitchOn.map {
                    it.filePath;
                } as ArrayList<String>
                val intent = Intent()
                intent.putExtra(Constant.IntentPutKey.File_paths, paths)
                setResult(Activity.RESULT_OK, intent)
                bActivity.finish()
            }
        }
    }

    /**
     * viewPager初始化
     */
    private fun initViewPager() {
        initFileFragments()
        AdapterForViewPagerFile = ViewPagerFileAdapter(supportFragmentManager, fragments, types)
        viewPager_file_type.setAdapter(AdapterForViewPagerFile)
        AdapterForViewPagerFile!!.notifyDataSetChanged()

        //为TabLayout设置ViewPager
        tabLayout_file_type.setupWithViewPager(viewPager_file_type)
        tabLayout_file_type.post(Runnable { setIndicator(tabLayout_file_type, 4, 4) })
    }

    //初始化文件集合
    private fun initFileFragments() {
//        fragments.add(getFileFragment(arrayOf(Constant.DOC, Constant.DOCX, Constant.XLS, Constant.XLSX, Constant.PPT, Constant.PDF)))
        fragments.add(getFileFragment(arrayOf(Constant.DOC, Constant.DOCX)))
        fragments.add(getFileFragment(arrayOf(Constant.XLS, Constant.XLSX)))
        fragments.add(getFileFragment(arrayOf(Constant.PDF)))
        fragments.add(getFileFragment(arrayOf( Constant.PPT)))
        fragments.add(getFileFragment(arrayOf(Constant.APK)))
        fragments.add(getFileFragment(arrayOf(Constant.MP3)))
        fragments.add(getFileFragment(arrayOf(Constant.ZIP,Constant.RAR)))

        types.add("word")
        types.add("excal")
        types.add("pdf")
        types.add("ppt");
        types.add("应用")
        types.add("mp3")
        types.add("其他")

    }

    private fun getFileFragment(fileTypes: Array<String>): FileTypeFragment {
        val fragment = FileTypeFragment()
        val bundle = Bundle()
        bundle.putStringArray(Constant.FILE_TYPES, fileTypes)
        fragment.arguments = bundle
        return fragment
    }

    //修改下划线卡宽度
    fun setIndicator(tabs: TabLayout, leftDip: Int, rightDip: Int) {
        val tabLayout = tabs.javaClass
        var tabStrip: Field? = null
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip")
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        tabStrip!!.isAccessible = true
        var llTab: LinearLayout? = null
        try {
            llTab = tabStrip.get(tabs) as LinearLayout
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }

        val left = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip.toFloat(), Resources.getSystem().displayMetrics).toInt()
        val right = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip.toFloat(), Resources.getSystem().displayMetrics).toInt()

        for (i in 0 until llTab!!.childCount) {
            val child = llTab.getChildAt(i)
            child.setPadding(0, 0, 0, 0)
            val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
            params.leftMargin = left
            params.rightMargin = right
            child.layoutParams = params
            child.invalidate()
        }
    }

    private fun getIntSize(): Int {
        var sizeAll = 0
        for (i in filesByPitchOn.indices) {
            val fileSize = TransformUtil.StringToInt(filesByPitchOn[i].fileSize)
            sizeAll = sizeAll + fileSize
        }
        return sizeAll
    }

    fun getMaxSize(): Int {
        return maxNum
    }

    fun getFilesByPitchOn(): List<MyFile> {
        return filesByPitchOn
    }

}
