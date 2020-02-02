package com.example.zippy.api;

import com.example.zippy.model.Advertise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Posti {
    @POST("/advertise")
    Call<Void> addAdvertise(@Body Advertise advertise);

    @GET("/advertise")
    Call<List<Advertise>> getAdvertise();

}
