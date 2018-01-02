package com.philipli.smartzone.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.philipli.smartzone.app.SmartZoneApp;

/**
 * Created by philipli on 2018/1/1.
 */

public class SharedPreferenceUtil {

    private static final String CONFIG = "config";

    /**
     * 获取SharedPreferences实例对象
     *
     * @param fileName
     */
    private static SharedPreferences getSharedPreference(String fileName) {
        return SmartZoneApp.getInstance().getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    /**
     * 保存一个String类型的值
     */
    public static void putString(String key, String value) {
        SharedPreferences.Editor editor = getSharedPreference(CONFIG).edit();
        editor.putString(key, value).apply();
    }

    /**
     * 获取String的value
     */
    public static String getString(String key, String defValue) {
        SharedPreferences sharedPreference = getSharedPreference(CONFIG);
        return sharedPreference.getString(key, defValue);
    }

}
