package com.philipli.smartzone.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.philipli.smartzone.R;
import com.philipli.smartzone.app.SmartZoneApp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static List<Integer> getMyChannels() {
        String source = PreferenceUtil.getString(ConstantUtil.CHANNEL_KEY, "");
        if (source.length() == 0) {
            List<Integer> list = new ArrayList<>(Arrays.asList(0,1,2));
            PreferenceUtil.putString(ConstantUtil.CHANNEL_KEY, list2String(list));
            return list;
        }
        return string2List(source);
    }

    public static void setMyChannels(List<Integer> channels) {
        String s = list2String(channels);
        PreferenceUtil.putString(ConstantUtil.CHANNEL_KEY, s);
    }

    public static List<Integer> getAllChannels(List<Integer> myChannel) {
        int size = SmartZoneApp.getInstance().getResources().getStringArray(R.array.news_tabs).length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (!myChannel.contains(i)) {
                list.add(i);
            }
        }
        return list;


    }

    private static String list2String(List<Integer> list) {
        String ret = "";
        for (int i : list) {
            ret += i + ",";
        }
        return ret.substring(0, ret.length() - 1);
    }

    private static List<Integer> string2List(String s) {
        String[] m = s.split(",");
        List<Integer> list = new ArrayList<>();
        for (String e : m) {
            list.add(Integer.parseInt(e));
        }
        return list;
    }



}
