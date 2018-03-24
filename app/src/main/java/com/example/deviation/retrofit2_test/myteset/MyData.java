package com.example.deviation.retrofit2_test.myteset;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by conscious on 2017-11-12.
 */

public class MyData {

    @SerializedName("id")
    @Expose
    public String id;


    @SerializedName("contents")
    @Expose
    public String contents;


    @SerializedName("title")
    @Expose
    public String title;


    @SerializedName("message")
    @Expose
    public String message;

    @SerializedName("imageUrl")
    @Expose
    public String imageUrl;

    @SerializedName("action")
    @Expose
    public String action;

    @SerializedName("topic")
    @Expose
    public String topic;

    @SerializedName("token")
    @Expose
    public String token;

    @SerializedName("api")
    @Expose
    public String api;

    public MyData(String id, String contents) {
        this.id = id;
        this.contents = contents;
    }

    public MyData(String id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }


}