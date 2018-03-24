package com.example.deviation.retrofit2_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deviation.retrofit2_test.model.Item;
import com.example.deviation.retrofit2_test.model.SOAnswersResponse;
import com.example.deviation.retrofit2_test.myteset.ApiService;
import com.example.deviation.retrofit2_test.myteset.MyData;
import com.example.deviation.retrofit2_test.myteset.Retrofit2Client;
import com.example.deviation.retrofit2_test.util.ApiUtils;
import com.example.deviation.retrofit2_test.util.SOService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private AnswersAdapter mAdapter;
    private RecyclerView rv;
    private SOService service;
    private ApiService apiService;

    private TextView tv;
    private EditText et1, et2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        et1 = findViewById(R.id.et_1);
        et2 = findViewById(R.id.et_2);
        Button btn = findViewById(R.id.btn1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getMyData();
                sendPushToAppWhereIsMine();
            }
        });
        btn = findViewById(R.id.btn2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMyDataPost();

            }
        });

        mAdapter = new AnswersAdapter(this, new ArrayList<Item>(0), new AnswersAdapter.PostItemListener() {

            @Override
            public void onPostClick(long id) {
                Toast.makeText(MainActivity.this, "Post id is" + id, Toast.LENGTH_SHORT).show();
            }
        });

        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(mAdapter);
        rv.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rv.addItemDecoration(itemDecoration);


        service = ApiUtils.getSOService();
        apiService = ApiUtils.getApiService();
        loadAnswers();


    }

    public void loadAnswers() {
        service.getAnswers().enqueue(new Callback<SOAnswersResponse>() {
            @Override
            public void onResponse(Call<SOAnswersResponse> call, Response<SOAnswersResponse> response) {
                if (response.isSuccessful()) {
                    mAdapter.updateAnswers(response.body().getItems());
                    Log.d("MainActivity", "posts loaded from API");
                } else {
                    int statusCode = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<SOAnswersResponse> call, Throwable t) {
//                showErrorMessage();
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    private void getMyData() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(ApiService.MAIN_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        ApiService myParse = retrofit.create(ApiService.class);
//        Call<MyData> call = myParse.getMyData();
        apiService.getMyData().enqueue(new Callback<MyData>() {
            @Override
            public void onResponse(Call<MyData> call, Response<MyData> response) {
                MyData p = response.body();
                Log.i("onResponse", "myparsepost" + p.id);

                String abc = p.id + " : " + p.contents;
                tv.setText(abc);
            }

            @Override
            public void onFailure(Call<MyData> call, Throwable t) {
                Log.i("onFailure", "myparsepost" + t.getMessage());

            }
        });
    }


    private void getMyDataPost() {
        Retrofit2Client.getInstance()
                .apiService
                .getMyDataPost(et1.getText().toString(), et2.getText().toString())
                .enqueue(new Callback<MyData>() {
                    @Override
                    public void onResponse(Call<MyData> call, Response<MyData> response) {
                        MyData p = response.body();
                        Log.i("onResponse", "myparsepost" + p.id);
                        String abc = "id : " + p.id + "\ncontents : " + p.contents;
                        tv.setText(abc);
                    }

                    @Override
                    public void onFailure(Call<MyData> call, Throwable t) {
                        Log.i("onFailure", "myparsepost" + t.getMessage());
                    }
                });
    }

    private void sendPushToAppWhereIsMine() {
        String id = "input your token";
        Retrofit2Client.getInstance()
                .apiService
                .sendMessage(id, et1.getText().toString(), et2.getText().toString())
                .enqueue(new Callback<MyData>() {
                    @Override
                    public void onResponse(Call<MyData> call, Response<MyData> response) {
                        MyData p = response.body();
                        Log.i("onResponse", "myparsepost" + p.id);
//                        String abc = "id : " + p.id + "\ntitle : " + p.title + "\nmessage : " + p.message;
//                        tv.setText(abc);
                    }

                    @Override
                    public void onFailure(Call<MyData> call, Throwable t) {
                        Log.i("onFailure", "myparsepost" + t.getMessage());
                    }
                });
    }


}
