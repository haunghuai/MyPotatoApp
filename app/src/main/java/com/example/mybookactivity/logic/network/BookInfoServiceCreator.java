package com.example.mybookactivity.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookInfoServiceCreator {
    private BookInfoServiceCreator(){}
    public static BookInfoServiceCreator getInstance(){

        return ServiceCreatorHolder.sInstance;
    }
    private static class ServiceCreatorHolder{
        private static final BookInfoServiceCreator sInstance = new BookInfoServiceCreator();
     }

    private final String baseURL = "https://infosxs.pinzhu1688.com/";
    private Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(baseURL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    public  <T> T create(Class<T> serviceClass){

        return retrofit.create(serviceClass);
    }
}
