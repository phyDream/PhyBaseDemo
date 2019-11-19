package com.demo.phy.phybasedemo.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by 82353 on 2018/4/23.
 */
data class User (var name:String?) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }



}