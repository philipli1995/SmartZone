package com.philipli.smartzone.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by philip on 2017/10/13.
 */

public class CommonUtil {

    public static boolean hasNetWork(Context context) {
        NetworkInfo info = hasNetworkService(context);
        return info != null && info.isAvailable();
    }

    private static NetworkInfo hasNetworkService(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();

    }

}
