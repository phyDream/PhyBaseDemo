package com.demo.phy.phybasedemo.ui.imaging.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.base.BaseActivity
import com.demo.phy.phybasedemo.mvppresenter.ImageEditorsPresenter
import com.demo.phy.phybasedemo.mvpview.ImageEditorsView
import com.demo.phy.phybasedemo.utils.Constant
import kotlinx.android.synthetic.main.activity_image_editors.*
import me.kareluo.imaging.IMGEditActivity
import java.io.File
import android.net.Uri

/**
 * Created by phy on 2019/12/2
 */
class ImageEditorsActivity : BaseActivity<ImageEditorsView, ImageEditorsPresenter>(), ImageEditorsView {

    override fun getLayoutId(): Int {
        return R.layout.activity_image_editors
    }

    override fun get_Presenter(): ImageEditorsPresenter {
        return ImageEditorsPresenter(this)
    }

    private var path = ""

    companion object {

        private const val KEY_STR1 = "str1Key"
        private const val KEY_STR2 = "str2Key"
        private const val KEY_STR3 = "str3Key"
        private const val REQ_IMAGE_EDIT = 1

        fun start(context: Context) {
            var intent = Intent(context, ImageEditorsActivity::class.java)
            context.startActivity(intent)
        }

    }

    override fun initView() {
    }

    override fun initData() {

    }

    override fun initListener() {
        btn_imagEditors.setOnClickListener {
            path = et_path.text.toString()
            val uri  = Uri.fromFile(File(path))
            val appDir = File(Constant.APP_CACHE_IMAGE)
            if (!appDir.exists()) {
                appDir.mkdirs()
            }
            val file = File(appDir, System.currentTimeMillis().toString() + ".jpg")
            file.toURI()
            toEditImage(uri,file.absolutePath)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_IMAGE_EDIT -> {
                if (resultCode == Activity.RESULT_OK) {
                    onImageEditDone()
                }
            }
        }
    }

    fun toEditImage(uri: Uri, saveToPath: String) {
        startActivityForResult(
                Intent(this, IMGEditActivity::class.java)
                        .putExtra(IMGEditActivity.EXTRA_IMAGE_URI, uri)
                        .putExtra(IMGEditActivity.EXTRA_IMAGE_SAVE_PATH, saveToPath), REQ_IMAGE_EDIT
        )
    }

    fun onImageEditDone() {

    }

}