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

    public MyData(String id, String contents) {
        this.id = id;
        this.contents = contents;
    }
}