package com.outim.mechat.ui.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.app.MyApplication
import com.demo.phy.phybasedemo.data.bean.MyFile
import com.demo.phy.phybasedemo.ui.files.activity.FileClassifySelectActivity
import com.demo.phy.phybasedemo.ui.files.adpter.FileListAdapter
import com.demo.phy.phybasedemo.utils.*
import kotlinx.android.synthetic.main.fragment_file_type.*
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FileTypeFragment : Fragment() {

    private var fileTypes: Array<String>? = null
    private val files = ArrayList<MyFile>()//文件数据集合
    private var adapter: FileListAdapter? = null
    private var filesByPitchOn: MutableList<MyFile> = ArrayList()
    var maxNum = 9
    private var mFileClassifySelectActivity: FileClassifySelectActivity? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_file_type, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
//        showLoadingDialog()
        Thread{
            initFiles()
        }.start()
    }


    //控件初始化
    private fun init(){
        mFileClassifySelectActivity = activity as FileClassifySelectActivity?
        maxNum = mFileClassifySelectActivity?.getMaxSize()!!
        filesByPitchOn = mFileClassifySelectActivity?.getFilesByPitchOn() as MutableList<MyFile>//得到选中文件集合
        adapter = FileListAdapter(context, files, filesByPitchOn)
        listView_file_type.setAdapter(adapter)
        //设置item点击事件
        listView_file_type.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            if(filesByPitchOn.size + 1 > maxNum){
                ToastUtils.showToast(MyApplication.instance,"超出限制")
            }else {
                val file = files[position]
                if (filesByPitchOn.contains(file)) {
                    filesByPitchOn.remove(file)
                } else {
                    filesByPitchOn.add(file)
                }
                StringUtil.clipBord(activity, file.filePath)
                ToastUtils.showToast(this!!.activity!!,file.filePath)
                adapter?.notifyDataSetChanged()
            }
        })
    }


    private fun getIntSize(): Int {
        var sizeAll = 0
        for (i in filesByPitchOn.indices) {
            val fileSize = TransformUtil.StringToInt(filesByPitchOn[i].fileSize)
            sizeAll = sizeAll + fileSize
        }
        return sizeAll
    }


    /**
     * 数据源初始化
     */
    private fun initFiles() {
        val bundle = arguments
        fileTypes = bundle!!.getStringArray(Constant.FILE_TYPES)
        files.clear()
        files.addAll(FileUtils.getSpecificTypeOfFile(context, fileTypes))//添加对应类型的文件集合
        activity?.runOnUiThread {
//            dismissLoadingDialog()
            adapter?.notifyDataSetChanged()
        }

    }


}
