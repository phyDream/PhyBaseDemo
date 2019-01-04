package com.demo.phy.phybasedemo.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.demo.phy.phybasedemo.R
import com.bumptech.glide.request.RequestOptions



/**
 * Created by SHY-UI on 2018/6/14.
 */
object ImageLoadUtil{
    fun loadByUrl(context: Context, imageView: ImageView?, url: String){
        val options = RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.load_failed)
                .centerCrop()
        Glide.with(context).load(url)
                .apply(options)
//                .crossFade()
                .into(imageView!!)
    }
}