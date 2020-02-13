package com.rupesh.zippy_wearables;

import com.rupesh.zippy_wearables.serverresponse.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Useri {
    @FormUrlEncoded
    @POST("user/login")
    Call<UserResponse> login(@Field("username") String username, @Field("password") String password);
}
