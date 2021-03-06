package com.example.deviation.retrofit2_test.myteset;

import com.example.deviation.retrofit2_test.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Deviation on 2018-03-25.
 */

public class Retrofit2Client {

    private static Retrofit2Client instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    public ApiService apiService;

    private Retrofit2Client() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder();
//        okHttpBuilder.addInterceptor(new TokenInterceptor());

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor);
        }

        client = okHttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.MAIN_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public static Retrofit2Client getInstance() {
        if (instance == null) {
            instance = new Retrofit2Client();
        }

        return instance;
    }

    public ApiService getApiService() {
        return apiService;
    }

}
