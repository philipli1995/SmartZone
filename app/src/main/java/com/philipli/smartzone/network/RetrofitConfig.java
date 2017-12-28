package com.philipli.smartzone.network;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.philipli.smartzone.app.SmartZoneApp;
import com.philipli.smartzone.util.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

/**
 * Created by philipli on 2017/10/21.
 */

public class RetrofitConfig {

    private final static String API_GANKIO = "https://gank.io/api/data/";
    private final static String API_DOUBAN = "Https://api.douban.com/";
    private final static String API_NETEASE = "http://c.m.163.com/nc/article/";


    public static HttpClient getDoubanAPI() {
        return createRetrofit(HttpClient.class, API_DOUBAN);
    }

    public static HttpClient getNeteaseAPI() {
        return createRetrofit(HttpClient.class, API_NETEASE);
    }

    public static HttpClient getMeituAPI() {
        return createRetrofit(HttpClient.class, API_GANKIO);
    }




    public static <T> T createRetrofit(Class<T> c, String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(SmartZoneApp.getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(c);
    }


    public static <T> T crateRetrofit(Class<T> c) {
        Retrofit retrofit = new Retrofit.Builder()
                .client(SmartZoneApp.getOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(c);
    }

    public static OkHttpClient getClient() {
        Interceptor interceptor = new HttpLoggingInterceptor();
        Cache cache = new Cache(new File(SmartZoneApp.getInstance().getCacheDir().toURI()), 1024 * 1024 * 10);
        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(interceptor)
                .addNetworkInterceptor(new CacheInteceptor())
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                //.addInterceptor(new UserAgentInterceptor())
                .build();
    }


    /*
        User Agent Interceptor
     */
    private static class UserAgentInterceptor implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            return null;
        }
    }

    private static class CacheInteceptor implements Interceptor{
        @Override
        public Response intercept(Chain chain) throws IOException {
            int timeoutWithNetwork = 60 * 60;
            int timeoutWithoutNetwork = 60 * 60 * 24;
            Request request = chain.request();

            if (CommonUtil.hasNetWork(SmartZoneApp.getInstance())) {
                request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            }
            else {
                request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
            }
            Response response = chain.proceed(request);
            if (CommonUtil.hasNetWork(SmartZoneApp.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + timeoutWithNetwork)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + timeoutWithoutNetwork)
                        .build();
            }
            return response;
        }
    }

}
