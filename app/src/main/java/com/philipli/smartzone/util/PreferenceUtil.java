package com.philipli.smartzone.util;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.philipli.smartzone.app.SmartZoneApp;


/**
 * Created by philip on 2017/10/11.
 */

public class PreferenceUtil {


    public static boolean getBoolean(String key, boolean defValue) {
        return PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance()).getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance());
        SharedPreferences.Editor e = s.edit();
        e.putBoolean(key, value);
        e.apply();
    }

    public static void putInt(String key, int value) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance());
        SharedPreferences.Editor e = s.edit();
        e.putInt(key, value);
        e.apply();
    }

    public static int getInt(String key, int defValue) {
        return PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance()).getInt(key, defValue);
    }

    public static String getString(String key, String defValue) {
        return PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance()).getString(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences s = PreferenceManager.getDefaultSharedPreferences(SmartZoneApp.getInstance());
        SharedPreferences.Editor e = s.edit();
        e.putString(key, value);
        e.apply();
    }

}
