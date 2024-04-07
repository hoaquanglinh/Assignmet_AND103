package com.example.assignmet_ph43159;


import com.example.assignmet_ph43159.Model.Cay;
import com.example.assignmet_ph43159.Model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {
    String DOMAIN = "http://10.0.2.2:3000/";
    @GET("/api/list")
    Call<List<Cay>> getCays();

    @Multipart
    @POST("/api/register")
    Call<User> register(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password,
            @Part("email") RequestBody email,
            @Part("name") RequestBody name,
            @Part MultipartBody.Part avatar
    );

    @POST("/api/login")
    Call<User> login (@Body User user);

    @Multipart
    @POST("/api/add")
    Call<Cay> addCay(@PartMap Map<String, RequestBody> requestBodyMap,
                        @Part MultipartBody.Part imageCay);

    @DELETE("/api/delete/{id}")
    Call<Void> deleteCay(@Path("id") String id);

    @Multipart
    @PUT("/api/update/{id}")
    Call<Cay> updateCay(@PartMap Map<String, RequestBody> requestBodyMap,
                        @Path("id") String id,
                        @Part MultipartBody.Part imageCay
    );

    @Multipart
    @PUT("/api/update-no-image/{id}")
    Call<Cay> updateNoImage(@PartMap Map<String, RequestBody> requestBodyMap,
                               @Path("id") String id
    );

    @GET("/api/search")
    Call<List<Cay>> searchCay(@Query("key") String query);
    @GET("/api/giam-dan")
    Call<List<Cay>> getGiam();

    @GET("/api/tang-dan")
    Call<List<Cay>> getTang();

}
