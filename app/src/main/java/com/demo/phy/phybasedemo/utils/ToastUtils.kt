package com.demo.phy.phybasedemo.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by 82353 on 2018/5/2.
 */
object ToastUtils {
    private var toast: Toast? = null

    fun showToast(context: Context, content: String) {
        if (toast == null) {
            toast = Toast.makeText(context,
                    content,
                    Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(content)
        }
        toast!!.show()
    }
}