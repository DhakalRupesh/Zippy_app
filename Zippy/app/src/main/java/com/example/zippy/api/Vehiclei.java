package com.example.zippy.api;

import com.example.zippy.model.Advertise;
import com.example.zippy.model.Vehicles;
import com.example.zippy.serverresponse.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Vehiclei {

    @POST("/vehicles")
    Call<Void> addVehicle(@Body Vehicles vehicles);

    @GET("/vehicle")
    Call<Vehicles> getVehicle();

    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadLicenseImage(@Part MultipartBody.Part img);
}
