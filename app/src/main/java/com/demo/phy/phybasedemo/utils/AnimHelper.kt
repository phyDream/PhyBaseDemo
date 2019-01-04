package com.demo.phy.phybasedemo.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateInterpolator

/**
 * Created by 82353 on 2018/5/2.
 */
object AnimHelper {

    /**
     * 执行从右滑入动画
     * @param view
     * @param startX
     * @param endX
     * @param duration
     */
    fun doSlideRightIn(view: View, startX: Float, endX: Float, duration: Float) {
        val translationX = ObjectAnimator.ofFloat(view, "translationX", startX, endX)
        val alpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        val set = AnimatorSet()
        set.duration = duration.toLong()
        set.playTogether(translationX, alpha)
        set.start()
    }

    /**
     * 裁剪视图宽度
     * @param view
     * @param srcWidth
     * @param endWidth
     * @param duration
     */
    fun doClipViewWidth(view: View, srcWidth: Int, endWidth: Int, duration: Int) {
        val valueAnimator = ValueAnimator.ofInt(srcWidth, endWidth).setDuration(duration.toLong())
        valueAnimator.addUpdateListener { valueAnimator ->
            val width = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.width = width
            view.layoutParams = layoutParams
        }
        valueAnimator.interpolator = AccelerateInterpolator()
        valueAnimator.start()
    }

    /**
     * 裁剪视图宽度
     * @param view
     * @param srcHeight
     * @param endHeight
     * @param duration
     */
    fun doClipViewHeight(view: View, srcHeight: Int, endHeight: Int, duration: Int) {
        val valueAnimator = ValueAnimator.ofInt(srcHeight, endHeight).setDuration(duration.toLong())
        valueAnimator.addUpdateListener { valueAnimator ->
            val width = valueAnimator.animatedValue as Int
            val layoutParams = view.layoutParams
            layoutParams.height = width
            view.layoutParams = layoutParams
        }
        valueAnimator.interpolator = AccelerateInterpolator()
        valueAnimator.start()
    }
}