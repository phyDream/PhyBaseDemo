package com.demo.phy.phybasedemo.utils

import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.Surface

/**
 * Created by 82353 on 2018/5/2.
 */
object WindowUtils{

    /**
     * [获取当前窗口的旋转角度]
     *
     * @param activity activity
     * @return int
     */
    fun getDisplayRotation(activity: Activity): Int {
        when (activity.windowManager.defaultDisplay.rotation) {
            Surface.ROTATION_0 -> return 0
            Surface.ROTATION_90 -> return 90
            Surface.ROTATION_180 -> return 180
            Surface.ROTATION_270 -> return 270
            else -> return 0
        }
    }

    /**
     * [当前是否是横屏]
     *
     * @param context context
     * @return boolean
     */
    fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    /**
     * [当前是否是竖屏]
     *
     * @param context context
     * @return boolean
     */
    fun isPortrait(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    }

    /**
     * [调整窗口的透明度  1.0f,0.5f 变暗]
     *
     * @param from    from>=0&&from<=1.0f
     * @param to      to>=0&&to<=1.0f
     * @param context 当前的activity
     */
    fun dimBackground(from: Float, to: Float, context: Activity) {
        val window = context.window
        val valueAnimator = ValueAnimator.ofFloat(from, to)
        valueAnimator.duration = 500
        valueAnimator.addUpdateListener { animation ->
            val params = window.attributes
            params.alpha = animation.animatedValue as Float
            window.attributes = params
        }
        valueAnimator.start()
    }

    /**
     * [获取界面方向]
     */
    fun getScreenOrientation(activity: Activity): Int {
        val rotation = activity.windowManager.defaultDisplay.rotation
        val dm = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(dm)
        val width = dm.widthPixels
        val height = dm.heightPixels
        val orientation: Int
        // if the device's natural orientation is portrait:
        if ((rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180) && height > width || (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) && width > height) {
            when (rotation) {
                Surface.ROTATION_0 -> orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                Surface.ROTATION_90 -> orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                Surface.ROTATION_180 -> orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
                Surface.ROTATION_270 -> orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                else -> orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        } else {
            when (rotation) {
                Surface.ROTATION_0 -> orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                Surface.ROTATION_90 -> orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                Surface.ROTATION_180 -> orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE
                Surface.ROTATION_270 -> orientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT
                else -> orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }// if the device's natural orientation is landscape or if the device
        // is square:

        return orientation
    }
}