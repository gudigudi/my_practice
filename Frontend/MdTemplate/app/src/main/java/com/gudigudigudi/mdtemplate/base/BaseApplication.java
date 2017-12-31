package com.gudigudigudi.mdtemplate.base;

import android.app.Application;
import android.content.Context;
import android.support.text.emoji.EmojiCompat;
import android.support.text.emoji.bundled.BundledEmojiCompatConfig;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.gudigudigudi.mdtemplate.BuildConfig;
import com.gudigudigudi.mdtemplate.crash.AppUncaughtExceptionHandler;
import com.gudigudigudi.mdtemplate.db.AppDBHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;

import okhttp3.OkHttpClient;

/**
 * Created by gu on 10/9/17.
 * 初始化SDK和应用需要的库.
 * 注册动态broadcast receiver.
 * 创建管理service.
 * 管理应用的实际开始点.
 */

public class BaseApplication extends Application {

    // Global context of Application.
    private static Context context;

    private AppDBHelper appDBHelper;
    public static final String APP_DB_NAME = "app.db";
    public static final int APP_DB_VERSION = 1;

    @Override
    public void onCreate() {
        super.onCreate();

        Thread.setDefaultUncaughtExceptionHandler(new AppUncaughtExceptionHandler());

        // LeakCanary: memory leak dectection tool.
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        // Facebook stetho: debug with chrome dev-tool
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        // Emoji.
        EmojiCompat.Config config = new BundledEmojiCompatConfig(this);
        EmojiCompat.init(config);

        context = getApplicationContext();

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());

//        appDBHelper= new AppDBHelper(context, APP_DB_NAME,null,APP_DB_VERSION);
//        appDBHelper.getWritableDatabase();

        // ARouter
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
            ARouter.printStackTrace();
        }
        ARouter.init(this);
    }

    public static Context getContext() {
        return context;
    }
}
