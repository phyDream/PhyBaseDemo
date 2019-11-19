package com.demo.phy.phybasedemo.utils;

import android.content.Context;

public class ConfigInfo {

    private static Context context;
    private static ConfigInfo instance;

    public static ConfigInfo init(Context context) {
        if (instance == null) {
            instance = new ConfigInfo(context);
        }
        return instance;
    }

    private ConfigInfo(Context context) {
        this.context = context;
        MyModulePreference.getInstance(context);
    }

    public static String getUid() {
        String uid = MyModulePreference.getInstance(context).getString(Constant.UID, "");
        if (uid == null) {
            uid = "";
        }
        return uid;
    }


    public static void setUid(String uid) {
        MyModulePreference.getInstance(context).put(Constant.UID, uid);
    }




}
