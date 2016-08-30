package com.xy.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.xy.retrofit.http.ApiFactory;
import com.xy.retrofit.http.GithubService;
import com.xy.retrofit.http.RetrofitClient;
import com.xy.retrofit.model.Repo;
import com.xy.retrofit.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getUserInfo();
        listRepos();
    }

    public void listRepos() {
        Call<List<Repo>> repoListCall = ApiFactory.getGithubService(this).listRepos("zhou411424");
        repoListCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                List<Repo> body = response.body();
                Log.d(TAG, "onResponse==>"+(body != null ? body.toString() : "body==null"));
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Log.e(TAG, "onFailure==>err msg=" + t.getMessage());
            }
        });
    }

    public void getUserInfo() {
        GithubService githubService = ApiFactory.getGithubService(this);
        Call<User> userCall = githubService.userInfo("zhou411424");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.d(TAG, "onResponse==>"+user != null ? "user info="+user.toString() : "user == null");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure==>");
                if (call.isCanceled()) {
                    Log.d(TAG, "onFailure==>call is canceled...");
                } else {
                    Log.e(TAG, "onFailure==>err msg: " + t.getMessage());
                }
            }
        });
    }
}
