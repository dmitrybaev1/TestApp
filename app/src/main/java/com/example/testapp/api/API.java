package com.example.testapp.api;

import com.example.testapp.api.response.ConfigurationRootApiResponse;
import com.example.testapp.api.response.UserRootApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface API {
    @GET("3e7f17e2cea043ad5d4179da7b884163c34f51d1/test.json")
    Call<ConfigurationRootApiResponse> getConfiguration();

    @GET
    Call<UserRootApiResponse> addEmployee(@Url String url);
}
