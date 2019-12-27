package com.example.zippy.interface_zippy;

import com.example.zippy.model_zippy.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Useri {
    @GET("user/users")
    Call<List<User>> getuser();

    @POST("user/register")
    Call<Void> addUser(@Body User user);
}
