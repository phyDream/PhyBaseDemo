package com.demo.phy.phybasedemo.utils

import android.annotation.SuppressLint
import android.text.TextUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by 82353 on 2018/5/2.
 */
object TimeUtils {

    //毫秒级
    fun getCurrentTimeMillis(): String {
        return System.currentTimeMillis().toString() + ""
    }

    //秒
    fun getGTM(): String {
        return (System.currentTimeMillis() / 1000).toString() + ""
    }

    fun getZoneGtm(): String {
        return (System.currentTimeMillis() / 1000).toString() + ""
    }


    //格林威治标准时间
    fun getGMTUnixTimeByCalendar(): Long {
        return System.currentTimeMillis() / 1000
    }

    fun getDate(): String {
        val format1 = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format1.format(Date())
    }

    /**
     * date格式转unixTime
     * @param date
     */
    fun getTimeStamp(date: String): Long {
        val formate = SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss")
        var timeStamp: Date? = null
        try {
            timeStamp = formate.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return if (timeStamp == null) {
            getGMTUnixTimeByCalendar()
        } else timeStamp.time / 1000
    }

    //获取当天0点unixTime
    fun getStartTime(): Long {
        val todayStart = Calendar.getInstance()
        todayStart.set(Calendar.HOUR_OF_DAY, 0)
        todayStart.set(Calendar.SECOND, 0)
        todayStart.set(Calendar.MINUTE, 0)
        todayStart.set(Calendar.MILLISECOND, 0)
        return todayStart.time.time / 1000
    }

    /**
     * 比较时间大小
     *
     * @param DATE1
     * @param DATE2
     * @return 后面时间大则 返回true 否则返回false
     */
    fun isBeforBig(DATE1: String, DATE2: String): Boolean {

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val dt1 = df.parse(DATE1)
            val dt2 = df.parse(DATE2)
            return if (dt1.time > dt2.time) {
                false
            } else
                dt1.time < dt2.time
        } catch (exception: Exception) {
            exception.printStackTrace()
            return true
        }

    }


    /**比较时间差距大
     * @param DATE1
     * @param DATE2
     * @return true -差1小时 false  不到一小时
     */
    fun isBeforTime(DATE1: String, DATE2: String): Boolean {

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val dt1 = df.parse(DATE1)
            val dt2 = df.parse(DATE2)
            val diff: Long
            if (dt1.time > dt2.time) {
                diff = dt1.time - dt2.time
            } else if (dt1.time < dt2.time) {
                diff = dt2.time - dt1.time
            } else {
                diff = 0
            }
            if (diff > 1000 * 60) {
                return true
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            return false
        }

        return false
    }

    /**unix时间转date时间
     * @param unixTime
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    fun unix2Date(unixTime: String): String {
        var unixTime = unixTime
        if (isValidDate(unixTime)) {
            unixTime = getTimeStamp(unixTime).toString() + ""
        }
        val timestamp = java.lang.Long.parseLong(unixTime) * 1000
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Date(timestamp))
    }

    /**获取unix时间当天日期
     * @param unixTime
     * @return
     */
    fun unix2Day(unixTime: String): String {
        return SimpleDateFormat("yyyy-MM-dd").format(Date(java.lang.Long.parseLong(unixTime) * 1000))
    }

    /**
     * 获取中文格式的时间
     * @return
     */
    fun getChineseDate(date: Date): String {
        val format = SimpleDateFormat("MM月dd日")
        return format.format(date)
    }

    /**
     * 获取收藏时间 yyyy-MM-dd
     * @return
     */
    fun getCollectionDate(): String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        return format.format(Date())
    }

    /**
     * 资讯类时间显示
     */
    fun getShowTime(info_date: String?): String {
        if (info_date == null || info_date.length == 0) {
            return "未知"
        }

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val todayStr = df.format(Date())
        return if (info_date.substring(0, 10) == todayStr.substring(0, 10)) {
            info_date.substring(11)
        } else {
            info_date.substring(0, 10)
        }
    }

    /**
     * 只显示日期
     * @param versionDate
     * @return
     */
    fun formatShowDate(versionDate: String): String {
        return if (TextUtils.isEmpty(versionDate)) {
            "未知"
        } else versionDate.substring(0, 10)
    }


    /**判断时间格式 格式必须为“YYYY-MM-dd”
     * @param str
     * @return true-是标准时间格式
     * false-不是标准时间格式
     */
    fun isValidDate(str: String): Boolean {
        //String str = "2007-01-02";
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        try {
            val date = formatter.parse(str)
            return str == formatter.format(date)
        } catch (e: Exception) {
            return false
        }

    }

    /**
     * 获取当天0点的时间戳
     * @return
     */
    fun getTimesmorning(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 获取当前时间戳
     * @return
     */
    fun getCurrentTimes(): Int {
        val cal = Calendar.getInstance()
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 获取本周一0点时间戳
     * @return
     */
    fun getTimesWeekmorning(): Int {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        cal.set(Calendar.HOUR_OF_DAY, 0)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 获得本月第一天0点时间
     */
    fun getTimesMonthmorning(): Int {
        val cal = Calendar.getInstance()
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0)
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH))
        return (cal.timeInMillis / 1000).toInt()
    }

    /**
     * 当前年的开始时间，即 01-01 00:00:00
     *
     * @return
     */
    fun getCurrentYearStartTime(): Int {
        val cal = Calendar.getInstance()
        cal.set(cal.get(Calendar.YEAR), 1, 1, 0, 0, 0)
        cal.set(Calendar.DAY_OF_YEAR, cal.getActualMinimum(Calendar.DAY_OF_YEAR))
        return (cal.timeInMillis / 1000).toInt()
    }

}