package com.example.web.api;

import com.example.web.models.PostItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPI {

    @POST("/posts/")
    Call<PostItem> post_posts(@Body PostItem post);

    @POST("/posts/{pk}/")
    Call<PostItem> post_posts(@Path("pk") int pk, @Body PostItem post);

    @DELETE("/posts/{pk}/")
    Call<PostItem> post_posts(@Path("pk") int pk);

    @GET("/posts/")
    Call<List<PostItem>> get_posts();

    @GET("/posts/{pk}/")
    Call<PostItem> get_post_pk(@Path("pk") int pk);
}
