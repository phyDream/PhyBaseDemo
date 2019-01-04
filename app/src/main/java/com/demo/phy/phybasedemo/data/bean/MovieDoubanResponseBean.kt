package com.demo.phy.phybasedemo.data.bean

/**
 * Created by XiaoJun on 2017/7/25.
 * Version 1.0.0
 */

data class MovieDoubanResponseBean(
        var count:Int?,
        var start:Int?,
        var total:Int?,
        var subjects:MutableList<MovieBeanInDouBan>,
        var title:String?
    ) {

    data class MovieBeanInDouBan(
        var rating:Rating?,
        var genres:MutableList<String>?,
        var title:String?,
        var casts:MutableList<Cast>?,
        var collect_count:Int?,
        var original_title:String?,
        var subtype:String?,
        var directors:MutableList<Cast>?,
        var year:String?,
        var images:Avatar?,
        var alt:String?,
        var id: String?
    )

    public data class Rating(var max:Double?,var average:Double?,var stars:String?,var min:Double?)

    data class Cast(var alt:String?,var avatars:Avatar?,var name:String?,var id:String?)      //主演

    data class Avatar(var small:String?,var large:String?,var medium:String?)   //不同尺寸的照片链接



}