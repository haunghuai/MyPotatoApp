package com.example.mybookactivity.logic.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookIndexService {

    @GET("BookFiles/Html/{id1}/{id2}/index.html")
    Call<ResponseBody> getBookIndex(@Path("id1") int id1, @Path("id2")int id2);
}
