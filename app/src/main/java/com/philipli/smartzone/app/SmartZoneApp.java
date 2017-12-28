package com.philipli.smartzone.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.philipli.smartzone.network.RetrofitConfig;

import okhttp3.OkHttpClient;

/**
 * Created by philipli on 2017/11/28.
 */

public class SmartZoneApp extends Application{

    public static SmartZoneApp mInstance;

    public static OkHttpClient mOkHttpClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Stetho.initializeWithDefaults(this);
        mOkHttpClient = RetrofitConfig.getClient();
    }

    public static SmartZoneApp getInstance() {
        return mInstance;
    }

    public static OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }


}
