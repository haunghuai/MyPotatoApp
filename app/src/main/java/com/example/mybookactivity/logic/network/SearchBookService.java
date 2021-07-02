package com.example.mybookactivity.logic.network;

import com.example.mybookactivity.logic.model.BookSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SearchBookService {
    //key={bookName}&page={pageid}&siteid=app2
    @POST("search.aspx")
    Call<BookSearch> getSearchBook(@Query("key")String bookName,@Query("page")int pageid,@Query("siteid")String siteid);
}
