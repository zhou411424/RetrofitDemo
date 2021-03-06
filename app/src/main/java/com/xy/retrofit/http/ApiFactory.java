package com.xy.retrofit.http;

import android.content.Context;

/**
 * Created by xingyun on 2016/8/30.
 */
public class ApiFactory {

    private static GithubService githubService;

    public static GithubService getGithubService(Context context) {
        if (githubService == null) {
            githubService = new RetrofitClient(context).getRetrofit().create(GithubService.class);
        }
        return githubService;
    }
}
