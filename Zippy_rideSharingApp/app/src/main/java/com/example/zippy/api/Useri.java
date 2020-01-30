package com.example.zippy.api;

import com.example.zippy.model.User;
import com.example.zippy.serverresponse.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Useri {

    @POST("user/register")
    Call<Void> addUser(@Body User user);

    @POST("user/login")
    Call<SignUpResponse> login(@Field("username") String username, @Field("password") String password);

    @GET("user/retriveme")
    Call<User> getme();
}
