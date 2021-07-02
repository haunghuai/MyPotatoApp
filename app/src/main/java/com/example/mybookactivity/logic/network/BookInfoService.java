package com.example.mybookactivity.logic.network;

import com.example.mybookactivity.logic.model.BookInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookInfoService {

     @GET("BookFiles/Html/{id1}/{id2}/info.html")
     Call<BookInfo> getBookInfo(@Path("id1")int id1, @Path("id2") int id2);


}
