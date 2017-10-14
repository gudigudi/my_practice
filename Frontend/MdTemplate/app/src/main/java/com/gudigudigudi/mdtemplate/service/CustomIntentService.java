package com.gudigudigudi.mdtemplate.service;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.orhanobut.logger.Logger;

/**
 * Created by gu on 10/14/17.
 */

public class CustomIntentService extends IntentService {

    public CustomIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Logger.d("Thread id is " + Thread.currentThread().getId());
    }
}
