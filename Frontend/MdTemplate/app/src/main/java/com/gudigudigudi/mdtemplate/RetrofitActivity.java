package com.gudigudigudi.mdtemplate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gudigudigudi.mdtemplate.model.Repo;
import com.gudigudigudi.mdtemplate.web.service.GitHubService;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
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
    }
}
