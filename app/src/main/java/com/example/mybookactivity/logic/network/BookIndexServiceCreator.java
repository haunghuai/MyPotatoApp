package com.example.mybookactivity.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookIndexServiceCreator {
    public BookIndexServiceCreator(){}
    public static BookIndexServiceCreator getInstance(){

        return ServiceCreatorHolder.sInstance;
    }
    private static class ServiceCreatorHolder{
        private static final BookIndexServiceCreator sInstance = new BookIndexServiceCreator();
     }

    private final String baseURL = "https://infosxs.pinzhu1688.com/";
    private Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(baseURL)
                                    .build();
    public  <T> T create(Class<T> serviceClass){
        return retrofit.create(serviceClass);
    }
}
