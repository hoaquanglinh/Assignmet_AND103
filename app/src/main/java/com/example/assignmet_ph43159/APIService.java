package com.example.assignmet_ph43159;


import com.example.assignmet_ph43159.Model.Cay;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String DOMAIN = "http://10.0.2.2:3000/";
    @GET("/api/list")
    Call<List<Cay>> getCays();

//    @Multipart
//    @POST("/api/add")
//    Call<Void> addCay(
//            @Part("ten") String ten,
//            @Part MultipartBody.Part anh,
//            @Part("gia") Double gia,
//            @Part ("kichthuoc") String kichthuoc
//    );

    @POST("/api/add")
    Call<Void> addCay(@Body Cay cay);

    @DELETE("/api/delete/{id}")
    Call<Void> deleteCay(@Path("id") String id);

    @PUT("/api/update/{id}")
    Call<Void> updateCay(
            @Path("id") String id,
            @Body Cay cay
    );

}
