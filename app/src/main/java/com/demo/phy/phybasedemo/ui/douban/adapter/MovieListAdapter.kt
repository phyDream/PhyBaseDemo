package com.demo.phy.phybasedemo.ui.douban.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.RatingBar
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.demo.phy.phybasedemo.R
import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import com.demo.phy.phybasedemo.utils.ImageLoadUtil

/**
 * Created by 蒲弘宇的本地账户 on 2018/5/8.
 */
public class MovieListAdapter(layoutResId:Int,val context: Context,movies:List<MovieDoubanResponseBean.MovieBeanInDouBan> ): BaseQuickAdapter<MovieDoubanResponseBean.MovieBeanInDouBan, BaseViewHolder>(layoutResId,movies) {

    override fun convert(helper: BaseViewHolder?, item: MovieDoubanResponseBean.MovieBeanInDouBan?) {
        var rata = item?.rating?.average
        var url = item?.images?.medium
        //可链式调用赋值
        helper?.setText(R.id.tv_movieName, item?.title)

        var rating : RatingBar? = helper?.getView<RatingBar>(R.id.scoreInRating)
        rating?.rating  = rata?.toFloat()!! /2
        var poster : ImageView? = helper?.getView<ImageView>(R.id.img_moviePoster)
        ImageLoadUtil.loadByUrl(context,poster, url!!)

    }

}