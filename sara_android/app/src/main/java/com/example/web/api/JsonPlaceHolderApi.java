package com.example.web.api;

import com.example.web.models.LoginResponse;
import com.example.web.models.PostItem;
import com.example.web.models.input;
import com.example.web.models.output;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


// Only used this Interface //
public interface JsonPlaceHolderApi {

    /* userId 에 해당하는 값만 받아오도록 하는 부분*/
    @GET("api/fmuname")
    Call<List<input>> getPost();

    @GET("api/fmuinform")
    Call<List<output>> getPost1(@Query(value = "output") String fmuname);

    @GET("api/fmuinform")
    Call<List<PostItem>> getPost2(@Query(value = "input") String fmuname);

    // https://walkplanetcat.com/api/xlsx?planetkey="planetkey"&input="fmuname"
    @GET("api/xlsx")
    Call<List<PostItem>> getPost3(@Query(value="planetkey") String planetkey, @Query(value="input")String fmuname);

    // https://walkplanetcat.com/api/xlsx?planetkey="planetkey"&output="fmuname"
    @GET("api/xlsx")
    Call<List<output>> getPost4(@Query(value="planetkey") String planetkey, @Query(value="output")String fmuname);


    @POST("/user/login")
    @FormUrlEncoded
    Call<LoginResponse> login(
            @Field("name") String name,
            @Field("password") String password);


    /* userId 를 출력하고 정렬 방식을 조정하는 부분
    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int userId, @Query("_sort") String sort, @Query("_order") String desc);
    */

    /* 여러개의 userId를 지정해서 해당하는 userId 값을 받을 수 있도록 하는 부분
        여기서도 _sort 와 _order를 이용하면 정렬을 할 수 있다.
    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") int userId, @Query("userId") int userId2, @Query("userId") int userId3);
    */

    /* 여러개의 userId를 배열로 지정해서 해당하는 userId 값을 받을 수 있도록 하는 부분
        여기서도 _sort 와 _order를 이용하면 정렬을 할 수 있다.
    @GET("posts")
    Call<List<Post>> getPost(@Query("userId") Integer[] userId);
    */

    /* Map 을 이용해서 userId를 지정받도록 하는 부분
    @GET("posts")
    Call<List<PostItem>> getPost(@QueryMap Map<String, String> parameter);
    */
}
