package com.xy.retrofit.base;

import android.app.Application;
import android.content.Context;

import com.facebook.stetho.Stetho;

/**
 * Created by xingyun on 2016/8/30.
 */
public class RetrofitApplication extends Application {

    private Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = this;

        // 在chrome里输入chrome://inspect/#devices调试
//        Stetho.initializeWithDefaults(this);
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}
