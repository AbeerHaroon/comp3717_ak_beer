package com.example.projectprototype;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static Retrofit retrofit;

    private static Retrofit getRetrofit(){

        if(retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl("http://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


    public static Object getService(Class cs){
        return getRetrofit().create(cs);
    }
}

