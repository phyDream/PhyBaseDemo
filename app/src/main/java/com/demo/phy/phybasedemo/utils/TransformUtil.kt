package com.demo.phy.phybasedemo.utils

import java.text.DecimalFormat

/**
 * Created by 82353 on 2018/5/2.
 */
object TransformUtil {
    //string转int
    fun StringToInt(str: String): Int {
        return Integer.parseInt(str)
    }

    //int转string
    fun IntToString(i: Int): String {
        return i.toString()
    }

    //string转float
    fun StringToFloat(str: String): Float {
        var f = 0f
        try {
            f = java.lang.Float.parseFloat(str)
        } catch (e: NumberFormatException) {
        }

        return f
    }

    //转换文件大小（string）
    fun getFileSize(size: Int): String? {
        val fileSize = size.toFloat() / 1000
        val decimalFormat = DecimalFormat("0.00")//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (fileSize < 1000) {
            return decimalFormat.format(fileSize.toDouble()) + "KB"
        } else if (fileSize >= 1000) {
            return decimalFormat.format((fileSize / 1000).toDouble()) + "MB"
        } else if (fileSize >= 1000000) {
            return decimalFormat.format((fileSize / 1000000).toDouble()) + "GB"
        }
        return null
    }
}