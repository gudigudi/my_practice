package com.gudigudigudi.commonlib.base;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

/**
 * Created by gu on 10/9/17.
 * 初始化SDK和应用需要的库.
 * 注册动态broadcast receiver.
 * 创建管理service.
 * 管理应用的实际开始点.
 */
public class BaseApplication extends Application {

    public static final int APP_DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
