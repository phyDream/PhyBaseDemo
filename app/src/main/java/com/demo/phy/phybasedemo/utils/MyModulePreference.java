package com.demo.phy.phybasedemo.utils;

import android.content.Context;

import net.grandcentrix.tray.TrayPreferences;

public class MyModulePreference extends TrayPreferences {

    public static String KEY_IS_FIRST_LAUNCH = "first_launch";
    private  static MyModulePreference myModulePreference;

    public MyModulePreference(final Context context) {
        super(context, "myModule", 1);
    }

    public static MyModulePreference getInstance(Context context) {
        if (myModulePreference == null) {
            myModulePreference = new MyModulePreference(context);
        }

        return myModulePreference;
    }

}