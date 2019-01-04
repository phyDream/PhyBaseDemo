package com.demo.phy.phybasedemo.data.network.api

import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Created by 82353 on 2018/4/27.
 */

interface DoubanApi{
    companion object {
        val baseUrl:String = "https://api.douban.com/"
    }

    /**
     * 得到影院当前热映电影
     * https://api.douban.com/v2/movie/in_theaters
     * @start:相当于查询偏移量
     */
    @GET("v2/movie/in_theaters")
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0)")
    fun getTheaterMovie(@Query("start") start:Int ,@Query("count") count:Int): Observable<MovieDoubanResponseBean>

    /**
     * 得到即将上映的电影
     * https://api.douban.com/v2/movie/coming_soon
     */
    @GET("v2/movie/coming_soon")
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0)")
    fun getComingMovie(@Query("start") start:Int ,@Query("count") count:Int): Observable<MovieDoubanResponseBean>

    /**
     * Top250
     * https://api.douban.com/v2/movie/top250
     */
    @GET("v2/movie/top250")
    @Headers("User-Agent:Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0)")
    fun getTopMovie(@Query("start") start:Int ,@Query("count") count:Int): Observable<MovieDoubanResponseBean>

}