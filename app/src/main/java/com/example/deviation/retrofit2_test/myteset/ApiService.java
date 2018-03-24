package com.example.deviation.retrofit2_test.myteset;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by conscious on 2017-11-12.
 */

public interface ApiService {
    public static final String MAIN_URL = "http://jip.dothome.co.kr/php/retrofit2test/";
    public static final String SUB_URL = "test_p.php";
    public static final String SEND_URL = "send.php";


    @GET(SUB_URL)
    Call<MyData> getMyData();

    @FormUrlEncoded
    @POST(SUB_URL)
    Call<MyData> getMyDataPost(@Field("id") String id, @Field("contents") String contents);


    @FormUrlEncoded
    @POST(SEND_URL)
    Call<MyData> sendMessage(@Field("id") String id, @Field("title") String title, @Field("message") String message);








}
