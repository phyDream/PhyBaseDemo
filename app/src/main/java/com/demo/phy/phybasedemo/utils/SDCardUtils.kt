package com.demo.phy.phybasedemo.utils

import android.os.Build
import android.os.Environment
import android.os.StatFs
import java.io.File

/**
 * Created by 82353 on 2018/5/2.
 */
object SDCardUtils {

    /**
     * 获取SD卡的状态
     */
    fun getState(): String {
        return Environment.getExternalStorageState()
    }


    /**
     * SD卡是否可用
     *
     * @return 只有当SD卡已经安装并且准备好了才返回true
     */
    fun isAvailable(): Boolean {
        return getState() == Environment.MEDIA_MOUNTED
    }


    /**
     * 获取SD卡的根目录
     *
     * @return null：不存在SD卡
     */
    fun getRootDirectory(): File? {
        return if (isAvailable()) Environment.getExternalStorageDirectory() else null
    }


    /**
     * 获取SD卡的根路径
     *
     * @return null：不存在SD卡
     */
    fun getRootPath(): String? {
        val rootDirectory = getRootDirectory()
        return rootDirectory?.path
    }

    /**
     * 获取sd卡路径
     *
     * @return Stringpath
     */
    fun getSDPath(): String {
        var sdDir: File? = null
        val sdCardExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED   //判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory()//获取跟目录
        }
        return sdDir!!.toString()
    }

    /**
     * 获取空闲的空间大小
     * @param path  文件路径
     * @return  空间大小
     */
    fun getFreeSpaceBytes(path: String): Long {
        val freeSpaceBytes: Long
        val statFs = StatFs(path)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            freeSpaceBytes = statFs.availableBytes
        } else {

            freeSpaceBytes = statFs.availableBlocks * statFs.blockSize.toLong()
        }

        return freeSpaceBytes
    }

}