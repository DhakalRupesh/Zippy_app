package com.example.zippy.api;

import com.example.zippy.model.Advertise;
import com.example.zippy.model.AdvertiseMy;
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

    @GET("/advertise/myAdvertise")
    Call<List<Advertise>> getLoggedAdvertise(@Header("Authorization") String token);

    @PUT("/advertise/{id}")
    Call<Void> updateStatus(@Header("Authorization") String token, @Path("id") String id, @Body Advertise advertise);

    @GET("advertise/myAdvertise")
    Call<Advertise> getAdvertiseStatus(@Header("Authorization") String token);
    
    @Multipart
    @POST("/uploads")
    Call<ImageResponse> uploadPostImage(@Part MultipartBody.Part img);

}
