package com.example.finalapplication.Api;

import com.example.finalapplication.Model.LoginRequest;
import com.example.finalapplication.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface InterfaceAPI {

    @Headers("Accept: application/json")
    @POST("login")
    Call<LoginResponse> checkLogin(@Body LoginRequest login);
}
