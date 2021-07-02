package com.example.mybookactivity.logic.network;

import com.example.mybookactivity.logic.model.BookPassage;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookContentService {

    @GET("BookFiles/Html/{id1}/{id2}/{id3}.html")
    Call<BookPassage> getBookContent(@Path("id1")int id1, @Path("id2")int id2, @Path("id3")int id3);

}
