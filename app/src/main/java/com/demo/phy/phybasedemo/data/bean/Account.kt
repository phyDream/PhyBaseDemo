package com.demo.phy.phybasedemo.data.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by phy on 2019/11/18
 */

data class Account(val user: User, val id: String, val password : String):Parcelable{
    constructor(parcel: Parcel) : this(
            parcel.readParcelable(User::class.java.classLoader),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(user, flags)
        parcel.writeString(id)
        parcel.writeString(password)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Account> {
        override fun createFromParcel(parcel: Parcel): Account {
            return Account(parcel)
        }

        override fun newArray(size: Int): Array<Account?> {
            return arrayOfNulls(size)
        }
    }

}