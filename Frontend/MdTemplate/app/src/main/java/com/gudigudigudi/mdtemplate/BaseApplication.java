package com.gudigudigudi.mdtemplate;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.Logger;

/**
 * Created by gu on 10/9/17.
 */

public class BaseApplication extends Application {

    // Global context of Application.
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        Logger.addLogAdapter(new AndroidLogAdapter());
        Logger.addLogAdapter(new DiskLogAdapter());

    }

    public static Context getContext() {
        return context;
    }
}
