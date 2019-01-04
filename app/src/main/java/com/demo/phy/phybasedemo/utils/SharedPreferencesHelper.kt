package com.demo.phy.phybasedemo.utils

import android.content.Context
import android.content.SharedPreferences
import com.demo.phy.phybasedemo.app.MyApplication

/**
 * Created by 82353 on 2018/5/2.
 */
class SharedPreferencesHelper private constructor(){
    private val FILE_NAME = "userInfo"//用户信息
    private var mSharedPreferences: SharedPreferences// 单例
    private var instance: SharedPreferencesHelper? = null// 单例

    init {
        mSharedPreferences = MyApplication.mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
    }

    /**
     * 获取单例
     * @return
     */
    fun getInstance(): SharedPreferencesHelper? {
        if (instance == null) {
            synchronized(SharedPreferencesHelper :: class.java){
                if(instance == null){
                    instance = SharedPreferencesHelper()
                }
            }
        }
        return instance
    }

    /**
     * 清空SP
     */
    fun clear() {
        val editor = mSharedPreferences.edit()
        editor.clear()
        editor.commit()
    }

    /**
     * 保存数据
     *
     * @param key
     * @param data
     */
    fun saveData(key: String, data: Any) {
        val type = data.javaClass.simpleName

        val editor = mSharedPreferences.edit()

        if ("Integer" == type) {
            editor.putInt(key, data as Int)
        } else if ("Boolean" == type) {
            editor.putBoolean(key, data as Boolean)
        } else if ("String" == type) {
            editor.putString(key, data as String)
        } else if ("Float" == type) {
            editor.putFloat(key, data as Float)
        } else if ("Long" == type) {
            editor.putLong(key, data as Long)
        }
        editor.commit()
    }

    /**
     * 得到数据
     *
     * @param key
     * @param defValue
     * @return
     */
    fun getData(key: String, defValue: Any): Any? {

        val type = defValue.javaClass.simpleName
        if ("Integer" == type) {
            return mSharedPreferences.getInt(key, defValue as Int)
        } else if ("Boolean" == type) {
            return mSharedPreferences.getBoolean(key, defValue as Boolean)
        } else if ("String" == type) {
            return mSharedPreferences.getString(key, defValue as String)
        } else if ("Float" == type) {
            return mSharedPreferences.getFloat(key, defValue as Float)
        } else if ("Long" == type) {
            return mSharedPreferences.getLong(key, defValue as Long)
        }

        return null
    }
}