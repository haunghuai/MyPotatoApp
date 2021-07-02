package com.example.mybookactivity.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookContentServiceCreator {
    private BookContentServiceCreator(){}
    public static BookContentServiceCreator getInstance(){

        return ServiceCreatorHolder.sInstance;
    }
    private static class ServiceCreatorHolder{
        private static final BookContentServiceCreator sInstance = new BookContentServiceCreator();
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
