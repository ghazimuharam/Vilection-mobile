package com.example.finalapplication.Api;

import com.example.finalapplication.Model.CandidateResponse;
import com.example.finalapplication.Model.LoginRequest;
import com.example.finalapplication.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface InterfaceAPI {

    @Headers("Accept: application/json")
    @POST("login")
    Call<LoginResponse> checkLogin(@Body LoginRequest login);

    @Headers("Accept: application/json")
    @GET("candidate")
    Call<List<CandidateResponse>> getCandidate(@Header("Authorization") String authorization);
}
