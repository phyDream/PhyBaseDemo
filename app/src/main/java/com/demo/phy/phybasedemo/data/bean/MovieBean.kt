package com.demo.phy.phybasedemo.data.bean

import java.io.Serializable

/**
 * Created by 82353 on 2018/4/25.
 */
data class MovieBean(
        var madie: MediaBean? = null,
        var size: String? = "") : Serializable {
    data class Video(
        val id: Int? = 0,
        var path: String? = null,
        var title: String? = null,
        var displayName: String? = null,
        var thumbPath: String? = null,
        var mimeType: String? = null,
        var duration: Int? = 0,
        var size: String? = "") : Serializable
}
