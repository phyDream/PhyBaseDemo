package com.demo.phy.phybasedemo.data.network.managers

import com.demo.phy.phybasedemo.data.bean.MovieDoubanResponseBean
import com.demo.phy.phybasedemo.data.network.RetrofitClient
import com.demo.phy.phybasedemo.data.network.api.DoubanApi
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by 82353 on 2018/4/27.
 */
class DoubanNetworkManager private constructor(){

    var doubanApi:DoubanApi

    companion object {
        //单例
        private var instance:DoubanNetworkManager? = null
        fun newInstance():DoubanNetworkManager?{
            if(instance == null){
                synchronized(DoubanNetworkManager::class.java){
                    if(instance == null)
                        instance = DoubanNetworkManager()
                }
            }
            return instance
        }
    }

    init {
        doubanApi = RetrofitClient(DoubanApi.baseUrl).create(DoubanApi::class.java)!!
    }


    /**
     * [获得热门电影列表]
     */
    fun getTheaterMovie(start:Int, observer:Observer<MovieDoubanResponseBean>){

        var observable:Observable<MovieDoubanResponseBean> = doubanApi?.getTheaterMovie(start,12)

        observable.subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(observer)
    }

    /**
     * [获得即将上映电影列表]
     */
    fun getComingMovie(start:Int,observer:Observer<MovieDoubanResponseBean>){

        var observable:Observable<MovieDoubanResponseBean> = doubanApi?.getComingMovie(start,12)

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

    /**
     * [获得Top250电影列表]
     */
    fun getTopMovie(start:Int,observer:Observer<MovieDoubanResponseBean>){

        var observable:Observable<MovieDoubanResponseBean> = doubanApi?.getTopMovie(start,12)

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer)
    }

}


