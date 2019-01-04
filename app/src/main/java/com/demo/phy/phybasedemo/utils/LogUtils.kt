package com.demo.phy.phybasedemo.utils

/**
 * Created by 82353 on 2018/4/26.
 */
object LogUtils {

    //日志开关
    val DEBUG = true

    /**
     * Log default tag.
     */
    private val sTagDefault = "TAG"

    /**
     * Log toggle for release, default value is false.
     */
    private val sToggleRelease = false

    /**
     * Log toggle for print Throwable info, default value is true.
     */
    private val sToggleThrowable = true

    /**
     * Log toggle for print thread name, default value is false.
     */
    private val sToggleThread = false

    /**
     * Log toggle for print class name and method name, default value is false.
     */
    private val sToggleClassMethod = true

    /**
     * Log toggle for print file name and code line number, default value is
     * false.
     */
    private val sToggleFileLineNumber = false

    fun e(tag: String, msg: String, e: Throwable) {
        printLog(android.util.Log.ERROR, tag, msg, e)
    }

    fun e(msg: String, e: Throwable) {
        printLog(android.util.Log.ERROR, null, msg, e)
    }

    fun e(msg: String) {
        printLog(android.util.Log.ERROR, null, msg, null)
    }

    fun w(tag: String, msg: String, e: Throwable) {
        printLog(android.util.Log.WARN, tag, msg, e)
    }


    fun w(msg: String, e: Throwable) {
        printLog(android.util.Log.WARN, null, msg, e)
    }


    fun w(msg: String) {
        printLog(android.util.Log.WARN, null, msg, null)
    }

    fun i(tag: String, msg: String, e: Throwable) {
        printLog(android.util.Log.INFO, tag, msg, e)
    }


    fun i(msg: String) {
        printLog(android.util.Log.INFO, null, msg, null)
    }

    fun d(tag: String, msg: String, e: Throwable) {
        printLog(android.util.Log.DEBUG, tag, msg, e)
    }

    fun d(msg: String, e: Throwable) {
        printLog(android.util.Log.DEBUG, null, msg, e)
    }

    fun d(tag: String, msg: String) {
        printLog(android.util.Log.DEBUG, tag, msg, null)
    }

    fun d(msg: String) {
        printLog(android.util.Log.DEBUG, null, msg, null)
    }

    fun v(tag: String, msg: String, e: Throwable) {
        printLog(android.util.Log.VERBOSE, tag, msg, e)
    }

    fun v(tag: String, msg: String) {
        printLog(android.util.Log.VERBOSE, tag, msg, null)
    }

    fun v(msg: String) {
        printLog(android.util.Log.VERBOSE, null, msg, null)
    }

    private fun printLog(logType: Int, tag: String?, msg: String, e: Throwable?) {
        val tagStr = if (tag == null) sTagDefault else tag
        if (!DEBUG) {
            return
        }
        if (sToggleRelease) {
            if (logType < android.util.Log.INFO) {
                return
            }
            val msgStr = if (e == null)
                msg
            else
                msg + "\n" + android.util.Log
                        .getStackTraceString(e)

            when (logType) {
                android.util.Log.ERROR -> android.util.Log.e(tagStr, msgStr)
                android.util.Log.WARN -> android.util.Log.w(tagStr, msgStr)
                android.util.Log.INFO -> android.util.Log.i(tagStr, msgStr)
                else -> {
                }
            }

        } else {
            val msgStr = StringBuilder()

            if (sToggleThread || sToggleClassMethod || sToggleFileLineNumber) {
                val currentThread = Thread.currentThread()

                if (sToggleThread) {
                    msgStr.append("<")
                    msgStr.append(currentThread.name)
                    msgStr.append("> ")
                }

                if (sToggleClassMethod) {
                    val ste = currentThread.stackTrace[4]
                    val className = ste.className
                    msgStr.append("[")
                    msgStr.append(if (className == null)
                        null
                    else
                        className!!.substring((className!!
                                .lastIndexOf('.') + 1)))
                    msgStr.append("::")
                    msgStr.append(ste.methodName)
                    msgStr.append("] ")
                }

                if (sToggleFileLineNumber) {
                    val ste = currentThread.stackTrace[4]
                    msgStr.append("[")
                    msgStr.append(ste.fileName)
                    msgStr.append(":")
                    msgStr.append(ste.lineNumber)
                    msgStr.append("] ")
                }
            }

            msgStr.append(msg)
            if (e != null && sToggleThrowable) {
                msgStr.append('\n')
                msgStr.append(android.util.Log.getStackTraceString(e))
            }

            when (logType) {
                android.util.Log.ERROR -> android.util.Log.e(tagStr, msgStr.toString())
                android.util.Log.WARN -> android.util.Log.w(tagStr, msgStr.toString())
                android.util.Log.INFO -> android.util.Log.i(tagStr, msgStr.toString())
                android.util.Log.DEBUG -> android.util.Log.d(tagStr, msgStr.toString())
                android.util.Log.VERBOSE -> android.util.Log.v(tagStr, msgStr.toString())
                else -> {
                }
            }
        }
    }

}