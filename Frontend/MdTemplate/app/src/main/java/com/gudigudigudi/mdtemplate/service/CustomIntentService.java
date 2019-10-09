package com.gudigudigudi.mdtemplate.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import lombok.extern.slf4j.Slf4j;


/**
 * Created by gu on 10/14/17.
 */
@Slf4j
public class CustomIntentService extends IntentService {

    public CustomIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        log.debug("Thread id is " + Thread.currentThread().getId());
    }
}
