package com.example.zippy.api;

import com.example.zippy.model.Advertise;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Posti {
    @POST("/advertise")
    Call<Void> addAdvertise(@Body Advertise advertise);
}
