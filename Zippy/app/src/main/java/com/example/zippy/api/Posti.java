package com.example.zippy.api;

import com.example.zippy.model.Advertise;
import com.example.zippy.serverresponse.ImageResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Posti {
    @POST("/advertise")
    Call<Void> addAdvertise(@Body Advertise advertise);

    @GET("/advertise")
    Call<List<Advertise>> getAdvertise();

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

}
