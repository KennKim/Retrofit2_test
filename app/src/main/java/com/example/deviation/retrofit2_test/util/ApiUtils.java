package com.example.deviation.retrofit2_test.util;

import com.example.deviation.retrofit2_test.myteset.ApiService;

/**
 * Created by Deviation on 2018-03-23.
 */

public class ApiUtils  {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";
    public static final String MAIN_URL = "http://jip.dothome.co.kr";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
     public static ApiService getApiService() {
        return RetrofitClient.getClient(ApiService.MAIN_URL).create(ApiService.class);
    }


}