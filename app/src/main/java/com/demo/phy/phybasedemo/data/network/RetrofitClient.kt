package com.demo.phy.phybasedemo.data.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by 82353 on 2018/4/27.
 */
class RetrofitClient (baseUrl: String){

    var baseUrl:String? = null
    var mRetrofit: Retrofit? = null

    companion object {
        const val CONNECT_TIME_OUT :Long = 15
        const val READ_TIME_OUT :Long  = 20
        const val WRITE_TIME_OUT :Long  = 20

        var instance: RetrofitClient? = null
        fun getInstance(context: Context, baseUrl:String): RetrofitClient {
            if (instance == null){
                synchronized(RetrofitClient::class){
                    if (instance == null)
                        instance = RetrofitClient(baseUrl)
                }
            }
            return instance!!
        }
    }

    init {
        this.baseUrl = baseUrl

        //创建okHttpClient
        val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS)//连接超时时间
                .readTimeout(READ_TIME_OUT, TimeUnit.SECONDS)//读超时时间
                .writeTimeout(WRITE_TIME_OUT, TimeUnit.SECONDS)//写超时时间
                .retryOnConnectionFailure(true)//重连
                .build()
        //创建Retrofit
        mRetrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())//json解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    fun <T> create(service: Class<T>?): T? {
        if (service == null) {
            throw RuntimeException("Api service is null!")
        }
        return mRetrofit?.create(service)
    }

}