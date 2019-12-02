package com.demo.phy.phybasedemo.ui.files.activity

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.FilePresenter
import com.demo.phy.phybasedemo.mvpview.FileView
import com.demo.phy.phybasedemo.utils.LogUtils
import com.demo.phy.phybasedemo.utils.PermissionUtils
import com.leon.lfilepickerlibrary.LFilePicker
import kotlinx.android.synthetic.main.activity_file.*
import kotlinx.android.synthetic.main.top_bar_layout.*

/**
 * Created by phy on 2019/11/28
 */
class FileActivity : BaseActivity<FileView, FilePresenter>(), FileView {

    override fun getLayoutId() = R.layout.activity_file

    override fun get_Presenter(): FilePresenter {
        return FilePresenter(this)
    }


    companion object {
        const val REQUEST_CODE_CHOOSE_FILE = 1
        const val PERMISSION_REQUEST_CODE = 100001
        val BASIC_PERMISSIONS = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE
        )

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"

        fun start(context: Context) {
            var intent = Intent(context, FileActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
        center_title.text = "文件选择器"
    }

    override fun initData() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_CHOOSE_FILE) {
                if (data != null) {
                    val list = data.getStringArrayListExtra("paths")
                    if (list != null && list.size > 0) {
                        for (path in list) {
                            LogUtils.e(path)
                        }
                    }
                }
            }
        }
    }

    override fun initListener() {
        btn_toSdCard.setOnClickListener {
            LFilePicker()
                    .withActivity(bActivity)
                    .withRequestCode(REQUEST_CODE_CHOOSE_FILE)
                    .withStartPath("/storage/emulated/0")//指定初始显示路径
                    .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                    .withFileSize(50 * 1024 * 1024)//指定文件大小
                    .withMaxNum(9)
                    .start()
        }

        btn_toClassify.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //检查权限
                var has = PermissionUtils.checkPermissions(bActivity, Manifest.permission.READ_EXTERNAL_STORAGE)
                if (!has) {
                    PermissionUtils.requestPermissions(this, PERMISSION_REQUEST_CODE, *BASIC_PERMISSIONS)
                } else {
                    FileClassifySelectActivity.start(this@FileActivity,9)
                }
            }
        }
    }

    //授权回调
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FileClassifySelectActivity.start(this@FileActivity,9)
            } else {
                PermissionUtils.requestPermissionDialog(bActivity, "请在设置-权限管理中开启读取文件功能", "请在设置-权限管理中开启读取文件功能")
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
    }


}