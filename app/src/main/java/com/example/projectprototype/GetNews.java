package com.example.projectprototype;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetNews {
    @GET("v2/top-headlines?q=corona&country=ca&apiKey=1d8738e2f4fa42c582b56bfb3e68e209")
    Call<Data> getData();
}

