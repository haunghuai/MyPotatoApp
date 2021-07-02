package com.example.mybookactivity.logic.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchBookServiceCreator {
    private SearchBookServiceCreator(){}
    public static SearchBookServiceCreator getInstance(){

        return ServiceCreatorHolder.sInstance;
    }
    private static class ServiceCreatorHolder{
        private static final SearchBookServiceCreator sInstance = new SearchBookServiceCreator();
     }

    private final String baseURL = "https://souxs.syqcnfj.com/";
    private Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(baseURL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();
    public  <T> T create(Class<T> serviceClass){

        return retrofit.create(serviceClass);
    }
}
