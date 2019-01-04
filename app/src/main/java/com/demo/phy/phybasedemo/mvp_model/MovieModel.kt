package com.demo.phy.phybasedemo.mvp_model

import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import com.demo.phy.phybasedemo.data.network.managers.DoubanNetworkManager
import com.demo.phy.phybasedemo.utils.Constant
import io.reactivex.Observer

/**
 * Created by 82353 on 2018/4/27.
 */
class MovieModel private constructor(){

    companion object {
        private var instance:MovieModel? = null
        fun newInstance(): MovieModel? {
            if(instance == null){
                synchronized(MovieModel::class.java){
                    if(instance == null)
                        instance = MovieModel()
                }
            }
            return instance
        }
    }

    var doubanNetworkManager:DoubanNetworkManager
    init {
        doubanNetworkManager = DoubanNetworkManager.newInstance()!!
    }

    //region 请求网络数据
    fun getMovie(tag: String?, start:Int, observer: Observer<MovieDoubanResponseBean>){
        when(tag){
            Constant.THEATER -> doubanNetworkManager.getTheaterMovie(start,observer)
            Constant.COMING -> doubanNetworkManager.getComingMovie(start,observer)
            Constant.Top250 -> doubanNetworkManager.getTopMovie(start,observer)
            Constant.THEATER -> doubanNetworkManager.getTheaterMovie(start,observer)
            else -> doubanNetworkManager.getTheaterMovie(start,observer)
        }

    }
    //endregion

    //region 请求数据库数据
    //endregion

    //region请求sp数据
    //endregion

}