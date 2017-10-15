package com.gudigudigudi.mdtemplate.util;

import android.content.Context;
import android.provider.Settings;

import java.util.UUID;

/**
 * Created by gu on 10/15/17.
 */

public class DeviceUtil {

    public static String getAndroidID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getUniqueID() {
        return UUID.randomUUID().toString();
    }
}
