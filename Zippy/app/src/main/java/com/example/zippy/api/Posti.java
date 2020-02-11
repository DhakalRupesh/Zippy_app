package com.example.zippy.api;

import com.example.zippy.model.Advertise;
import com.example.zippy.model.User;
import com.example.zippy.serverresponse.ImageResponse;
import com.example.zippy.url.Url;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface Posti {
    @POST("/advertise")
    Call<Void> addAdvertise(@Body Advertise advertise);

    @GET("/advertise")
    Call<List<Advertise>> getAdvertise(@Header("Authorization") String token);

    @GET("/advertise/{id}")
    Call<List<Advertise>> getLoggedAdvertise(@Path("id") String id);

    @PUT("/advertise/{id}")
    Call<Advertise> updateStatus(@Header("Authorization") String token, @Body Advertise advertise);

    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadPostImage(@Part MultipartBody.Part img);

}
