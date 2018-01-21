package com.gudigudigudi.mdtemplate.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gudigudigudi.mdtemplate.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.listRepos("gudigudi");
        try {
            repos.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl("http://one.sinalwj.cn/")
                .build();

        HttpService httpService = retrofit1.create(HttpService.class);
        Call<Result<Content>> call = httpService.getUserInfo();
        call.enqueue(new Callback<Result<Content>>() {
            @Override
            public void onResponse(Call<Result<Content>> call, Response<Result<Content>> response) {
                Log.i("tag", response.body().content.name);
            }

            @Override
            public void onFailure(Call<Result<Content>> call, Throwable t) {
                Log.i("tag", "failed.");
            }
        });
    }
}
