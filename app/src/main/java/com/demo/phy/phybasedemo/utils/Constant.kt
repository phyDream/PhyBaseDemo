package com.demo.phy.phybasedemo.utils

/**
 * Created by 82353 on 2018/4/26.
 * 常量
 */
object Constant{
    const val TABDIVIDER = 4//tabs数量
    const val MUSIC = 0
    const val MOVIE = 1
    const val BOOK = 2

    /**
     * 电影相关
     */
    const val NET = "net"
    const val LOCAL = "local"

    const val THEATER = "Theater"
    const val COMING = "Coming"
    const val Top250 = "Top250"

    const val MOVIE_DATAIL_TAG = "MovieDatail"

    //region 编号
    const val TAG_MVP = 1
    const val TAG_DIALOG = 2
    const val TAG_IPC = 3
    const val TAG_FRAGMENT = 4
    const val TAG_NOTIFICATION = 5
    const val TAG_FILE = 6
    const val TAG_HOT_UPDATE = 7
    //endregion

    //region tray
    const val UID = "UID"
    //endregion

    //文件类型传输的键
    const val FILE_TYPES = "fileTypes"
    //文件存储格式
    const val JPEG = ".jpeg"
    const val JPG = ".jpg"
    const val AMR = ".amr"
    const val DOC = ".doc"
    const val DOCX = ".docx"
    const val TXT = ".txt"
    const val XLS = ".xls"
    const val XLSX = ".xlsx"
    const val PDF = ".pdf"
    const val PPT = ".ppt"
    const val APK = ".apk"
    const val ZIP = ".zip"
    const val RAR = ".rar"
    const val MP4 = ".mp4"
    const val MP3 = ".mp3"

    interface IntentPutKey {
        companion object {
            val File_paths = "paths" //文件（和LFilePicker接收方式一致，故不能改）
        }
    }
}