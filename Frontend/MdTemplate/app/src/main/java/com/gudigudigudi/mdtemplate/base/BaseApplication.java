package com.gudigudigudi.mdtemplate.base;

import android.app.Application;
import android.content.Context;

import com.gudigudigudi.mdtemplate.db.AppDBHelper;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by gu on 10/9/17.
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

        context = getApplicationContext();

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());

//        appDBHelper= new AppDBHelper(context, APP_DB_NAME,null,APP_DB_VERSION);
//        appDBHelper.getWritableDatabase();
    }

    public static Context getContext() {
        return context;
    }
}
