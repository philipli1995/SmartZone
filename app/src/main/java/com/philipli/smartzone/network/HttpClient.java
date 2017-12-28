package com.philipli.smartzone.network;

import com.philipli.smartzone.bean.BookDetailBean;
import com.philipli.smartzone.bean.BooksBean;
import com.philipli.smartzone.bean.MeituBean;
import com.philipli.smartzone.bean.NewsDetail;
import com.philipli.smartzone.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by philipli on 2017/12/5.
 */

public interface HttpClient {

    @GET("v2/book/search")
    Observable<BooksBean> getBook(@Query("tag") String tag, @Query("start") int start, @Query("count") int count);

    @GET("v2/book/{id}")
    Observable<BookDetailBean> getBookDetail(@Path("id") String id);

    @GET("{type}/{id}/{startPage}-20.html")
    Observable<Map<String, List<NewsSummary>>> getNewsList(
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPage") int startPage);

    @GET("{postId}/full.html")
    Observable<Map<String, NewsDetail>> getNewsDetail(@Path("postId") String postId);

    @GET
    Observable<ResponseBody> getNewsBodyHtmlPhoto(
            @Url String photoPath);


    @GET("{type}/{requestNumber}/{startPage}")
    Observable<MeituBean> getMeitu(@Path("type") String type, @Path("requestNumber") int requestNumber,
                                   @Path("startPage") int startPage);


}


