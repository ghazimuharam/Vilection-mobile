package com.example.finalapplication.Api;

import com.example.finalapplication.Model.*;
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

    @Headers("Accept: application/json")
    @GET("candidate/{candidateId}/vote")
    Call<CandidateVoteResponse> voteCandidate(@Header("Authorization") String authorization, @Path(value = "candidateId", encoded = true) String candidateId);

    @Headers("Accept: application/json")
    @GET("user")
    Call<UserResponse> getUser(@Header("Authorization") String authorization);
}
