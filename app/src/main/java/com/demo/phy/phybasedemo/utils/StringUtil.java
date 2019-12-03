package com.demo.phy.phybasedemo.utils;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2018/4/26.
 */

public class StringUtil {

    public static String getAudioTime(long duration) {
        return String.valueOf(duration / 1000.0);
    }

    public static String getTimeStamp() {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sDateFormat.format(System.currentTimeMillis());
    }

    public static boolean validPassword(String str) {
        String regex = "(?=.{8,21})(?=.*\\d)(?=.*[a-zA-Z])[\\x20-\\x7f]*";

        boolean isPass = str.matches(regex);
        return isPass;
    }

    public static boolean validAccount(String str) {
        String regex = "(?=.{6,11})(?=.*\\d)(?=.*[a-zA-Z])[\\x20-\\x7f]*";

        boolean isPass = str.matches(regex);
        return isPass;
    }

    public static String format(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;
        if (timeStamp >= todayStartMillis) {
            return "今天";
        }
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;
        if (timeStamp >= yesterdayStartMilis) {
            return "昨天";
        }
        long yesterdayBeforeStartMilis = yesterdayStartMilis - oneDayMillis;
        if (timeStamp >= yesterdayBeforeStartMilis) {
            return "前天";
        }
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timeStamp));
    }

    public static String formatTime(long timeStamp) {
        long curTimeMillis = System.currentTimeMillis();
        Date curDate = new Date(curTimeMillis);
        int todayHoursSeconds = curDate.getHours() * 60 * 60;
        int todayMinutesSeconds = curDate.getMinutes() * 60;
        int todaySeconds = curDate.getSeconds();
        int todayMillis = (todayHoursSeconds + todayMinutesSeconds + todaySeconds) * 1000;
        long todayStartMillis = curTimeMillis - todayMillis;//今天0点
        int oneDayMillis = 24 * 60 * 60 * 1000;
        long yesterdayStartMilis = todayStartMillis - oneDayMillis;//昨天0点
        long yesterdayBeforeStartMilis = yesterdayStartMilis - oneDayMillis;//前天0点
        long timeWeekStartMilis = getTimesWeekmorning();//本周一零点
        long theYearStartMilis = getYearStartMilis();//本年零点
        //今天
        if (timeStamp >= todayStartMillis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(new Date(timeStamp));
        }
        //昨天
        if (timeStamp >= yesterdayStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "昨天 " + getPeriod(timeStamp) + sdf.format(new Date(timeStamp));
        }
        //前天
        if (timeStamp >= yesterdayBeforeStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return "前天 " + getPeriod(timeStamp) + sdf.format(new Date(timeStamp));
        }
        //本周内
        if (timeStamp >= timeWeekStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            return changeweekOne(timeStamp) + " " + getPeriod(timeStamp) + sdf.format(new Date(timeStamp));
        }
        //今年1号 ~ 本周第一天
        if (timeStamp < timeWeekStartMilis && timeStamp >= theYearStartMilis) {
            SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            return sdf1.format(new Date(timeStamp)) + " " + getPeriod(timeStamp) + sdf2.format(new Date(timeStamp));
        }
        //今年1号之前的
        if (timeStamp < timeWeekStartMilis) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
            return sdf.format(new Date(timeStamp));
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(timeStamp));
    }

    @SuppressLint("WrongConstant")
    public static long getTimesWeekmorning() {
        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTimeInMillis();
    }

    private static long getYearStartMilis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis();
    }

    private static String getPeriod(long timeStamp) {

        long dayTime = getMorning(timeStamp);//当天凌晨

        if ((timeStamp - dayTime) < 1000 * 60 * 60 * 6) {
            return "凌晨";
        } else if ((timeStamp - dayTime) < 1000 * 60 * 60 * 12) {
            return "早上";
        } else if ((timeStamp - dayTime) < 1000 * 60 * 60 * 18) {
            return "下午";
        } else if ((timeStamp - dayTime) < 1000 * 60 * 60 * 24) {
            return "晚上";
        }
        return "";
    }

    private static long getMorning(long timeStamp) {
        long nowTime = timeStamp;
        long StartTime = nowTime - (nowTime + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);
        return StartTime;
    }


    /**
     * 获得当天零时零分零秒
     *
     * @return
     */
    public Date initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }


    /**
     * 输入时间戳变星期
     *
     * @param time
     * @return
     */
    public static String changeweekOne(long time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
//        long lcc = Long.valueOf(time);
//        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(time * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
// 获取指定日期转换成星期几
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }

    /**
     * 判断给定字符串时间是否为今日(效率不是很高，不过也是一种方法)
     *
     * @param sdate
     * @return boolean
     */
    public static boolean isToday(String sdate) {
        boolean b = false;
        Date time = toDate(sdate);
        Date today = new Date();
        if (time != null) {
            String nowDate = dateFormater2.get().format(today);
            String timeDate = dateFormater2.get().format(time);
            if (nowDate.equals(timeDate)) {
                b = true;
            }
        }
        return b;
    }

    /**
     * 将字符串转位日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };
    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();

    /**
     * 判断是否为今天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否为昨天(效率比较高)
     *
     * @param day 传入的 时间  "2016-06-28 10:10:30" "2016-06-28" 都可以
     * @return true今天 false不是
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) throws ParseException {

        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);

        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);

        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR)
                    - pre.get(Calendar.DAY_OF_YEAR);

            if (diffDay == -1) {
                return true;
            }
        }
        return false;
    }

    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
            DateLocal.set(new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA));
        }
        return DateLocal.get();
    }

    /**
     * 获取两个日期之间的间隔天数
     *
     * @return
     */
    public static boolean getGapCount(String startDate, String endDate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date_start = null;
        Date date_end = null;
        try {
            date_start = sdf.parse(startDate);
            date_end = sdf.parse(endDate);
            Calendar calendar = Calendar.getInstance();  //得到日历
            calendar.setTime(date_end);//把当前时间赋给日历
            calendar.add(Calendar.DAY_OF_MONTH, -7);  //设置为7天前
            Date before7days = calendar.getTime();   //得到7天前的时间
            if (before7days.getTime() < date_start.getTime()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static void clipBord(Context context, String texts) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(texts);
    }

    /*时间戳
     * 将时间转换为
     */
    public static long dateToStamp(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        return date.getTime();
    }

    public static String getCurDate(String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date());
    }

    public static String getFormatDate(long times, String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date(times));
    }

    public static String unix2Date(String unixTime) {
        if (isValidDate(unixTime)) {
            unixTime = getTimeStamp(unixTime) + "";
        }
        Long timestamp = Long.parseLong(unixTime) * 1000;
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp));
        return date;
    }

    /**
     * date格式转unixTime
     *
     * @param date
     */
    public static long getTimeStamp(String date) {
        DateFormat formate = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date timeStamp = null;
        try {
            timeStamp = formate.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (timeStamp == null) {
            return getGMTUnixTimeByCalendar();
        }
        return timeStamp.getTime() / 1000;
    }

    //格林威治标准时间
    public static long getGMTUnixTimeByCalendar() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 判断时间格式 格式必须为“YYYY-MM-dd”
     *
     * @param str
     * @return true-是标准时间格式
     * false-不是标准时间格式
     */
    public static boolean isValidDate(String str) {
        //String str = "2007-01-02";
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = formatter.parse(str);
            return str.equals(formatter.format(date));
        } catch (Exception e) {
            return false;
        }
    }

    //字符串转时间戳
    public static long getTime(String timeString) {
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d;
        try {
            d = sdf.parse(timeString);
            long l = d.getTime();
            return l;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMoneyFormat(double data) {
        return new Formatter().format("%.2f", data).toString();
    }

    public static String getMoneyFormatJ(double data) {
        return new Formatter().format("%.1f", data).toString();
    }

    public static String encode(String url) {
        try {
            String encodeURL = URLEncoder.encode(url, "UTF-8");
            return encodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" + e.getMessage();
        }
    }

    public static String decode(String url) {
        try {
            String decodeURL = URLDecoder.decode(url, "UTF-8");

            return decodeURL;
        } catch (UnsupportedEncodingException e) {
            return "Issue while encoding" + e.getMessage();
        }
    }

    public static String StringFormat(String url) {
        try {
            String formatUrl = url.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#39;")
                    .replaceAll("\r\n", "<br/>")
                    .replaceAll("\r", "<br/>")
                    .replaceAll("\r", "<br/>");
            return formatUrl;
        } catch (Exception e) {
            return url;
        }
    }

    public static String StringReFormat(String url) {
        try {
            String decodeUrl = "";
            try {
                decodeUrl = decode(url);
            } catch (Exception e) {
                decodeUrl = url;
            }

            String formatUrl = decodeUrl.replaceAll("&amp;", "&")
                    .replaceAll("&lt;", "<")
                    .replaceAll("&gt;", ">")
                    .replaceAll("&quot;", "\"")
                    .replaceAll("&#39;", "'")
                    .replaceAll("<br/>", "\r\n")
                    .replaceAll("<br/>", "\r")
                    .replaceAll("<br/>", "\n");

            return formatUrl;
        } catch (Exception e) {
            return url;
        }
    }

    public static String hidePhoneNumberPart(String pNumber) {

        pNumber = pNumber.replace(" ", "");
        if (!TextUtils.isEmpty(pNumber) && pNumber.length() > 6) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pNumber.length(); i++) {
                char c = pNumber.charAt(i);
                if (i >= 3 && i <= 6) {
                    sb.append('*');
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        } else {
            return pNumber;
        }
    }

    /**
     * 包括空格判断
     *
     * @param input
     * @return
     */
    public static boolean containSpace(CharSequence input) {
        return Pattern.compile("\\s+").matcher(input).find();
    }

    /**
     * Returns the file extension or an empty string iff there is no
     * extension. This method is a convenience method for obtaining the
     * extension of a url and has undefined results for other Strings.
     *
     * @param url
     * @return The file extension of the given url.
     */
    public static String getFileExtensionFromUrl(String url) {
        if (!TextUtils.isEmpty(url)) {
            int fragment = url.lastIndexOf('#');
            if (fragment > 0) {
                url = url.substring(0, fragment);
            }

            int query = url.lastIndexOf('?');
            if (query > 0) {
                url = url.substring(0, query);
            }

            int filenamePos = url.lastIndexOf('/');
            String filename =
                    0 <= filenamePos ? url.substring(filenamePos + 1) : url;

            // if the filename contains special characters, we don't
            // consider it valid for our matching purposes:
            if (!filename.isEmpty() &&
                    Pattern.matches("[a-zA-Z_0-9\\.\\-\\(\\)\\%]+", filename)) {
                int dotPos = filename.lastIndexOf('.');
                if (0 <= dotPos) {
                    return filename.substring(dotPos + 1);
                }
            }
        }

        return "";
    }

    /**
     * 根据地址拿文件名
     *
     * @param pathandname
     * @return
     */
    public static String getFileName(String pathandname) {

        int start = pathandname.lastIndexOf("/");
        int end = pathandname.lastIndexOf(".");
        if (start != -1 && end != -1) {
            return pathandname.substring(start + 1, end);
        } else {
            return null;
        }

    }

    /**
     * 获取指定文件大小(单位：字节)
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static long getFileSize(File file) throws Exception {
        if (file == null) {
            return 0;
        }
        long size = 0;
        if (file.exists()) {
            FileInputStream fis = null;
            fis = new FileInputStream(file);
            size = fis.available();
        }
        return size;
    }

    //转换文件大小（string）
    public static String getFileSizeWithUnit(long size) {
        float fileSize = (float) (size) / 1024;
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (fileSize < 1024) {
            return decimalFormat.format(fileSize) + "KB";
        } else if (fileSize >= 1024) {
            return decimalFormat.format(fileSize / 1024) + "MB";
        }
        return null;
    }

    public static void copyText(Context context, String text, String toastStr) {
        //1. 复制字符串到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setPrimaryClip(ClipData.newPlainText(null, text));
        if (!TextUtils.isEmpty(toastStr)) {
            Toast.makeText(context, toastStr, Toast.LENGTH_SHORT).show();
        }
    }

    public static String omitSTring(String text, int count) {
        if (text.length() > count) {
            return text.substring(0, count) + "...";
        } else {
            return text + "";
        }

    }

    public static String getTimeStrFromTimestamp(long time) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
    }

    //string转int
    public static int StringToInt(String str) {
        return Integer.parseInt(str);
    }

    //转换文件大小（string）
    public static String getFileSize(int size) {
        float fileSize = (float) (size) / 1000;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        if (fileSize < 1000) {
            return decimalFormat.format(fileSize) + "KB";
        } else if (fileSize >= 1000) {
            return decimalFormat.format(fileSize / 1000) + "MB";
        }
        return null;
    }
}
