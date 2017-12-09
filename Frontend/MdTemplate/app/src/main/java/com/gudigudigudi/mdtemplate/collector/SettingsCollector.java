package com.gudigudigudi.mdtemplate.collector;

import android.content.ContentResolver;
import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by gu on 12/9/17.
 */

public class SettingsCollector {

    private final static String TAG = "SettingsCollector";

    private final Context mContext;

    public SettingsCollector(Context context) {
        mContext = context;
    }

    @NonNull
    public String collectSystemSettings() {
        final StringBuilder result = new StringBuilder();
        final Field[] keys = Settings.System.class.getFields();

        for (final Field key : keys) {
            if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class) {
                try {
                    final Object value = Settings.System.getString(mContext.getContentResolver(), (String) key.get(null));

                    if (value != null) {
                        result.append(key.getName()).append('=').append(value).append('\n');
                    }
                } catch (@NonNull IllegalArgumentException e) {
                    Log.w(TAG, "Error : ", e);
                } catch (@NonNull IllegalAccessException e) {
                    Log.w(TAG, "Error : ", e);
                }
            }
        }

        return result.toString();
    }

    public String collectSecureSettings() {
        final StringBuilder result = new StringBuilder();
        final Field[] keys = Settings.Secure.class.getFields();

        for (final Field key : keys) {
            if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class && isAuthorized(key)) {
                try {
                    final Object value = Settings.Secure.getString(mContext.getContentResolver(), (String) key.get(null));

                    if (value != null) {
                        result.append(key.getName()).append('=').append(value).append('\n');
                    }
                } catch (@NonNull IllegalArgumentException e) {
                    Log.w(TAG, "Error : ", e);
                } catch (@NonNull IllegalAccessException e) {
                    Log.w(TAG, "Error : ", e);
                }
            }
        }

        return result.toString();
    }

    public String collectGlobalSettings() {
        final StringBuilder result = new StringBuilder();

        try {
            final Class<?> globalClass = Class.forName("android.provider.Settings$Global");
            final Field[] keys = Settings.Secure.class.getFields();
            final Method getString = globalClass.getMethod("getString", ContentResolver.class, String.class);

            for (final Field key : keys) {
                if (!key.isAnnotationPresent(Deprecated.class) && key.getType() == String.class && isAuthorized(key)) {

                    final Object value = getString.invoke(null, mContext.getContentResolver(), key.get(null));

                    if (value != null) {
                        result.append(key.getName()).append('=').append(value).append('\n');
                    }
                }
            }
        } catch (@NonNull IllegalArgumentException e) {
            Log.w(TAG, "Error : ", e);
        } catch (@NonNull InvocationTargetException e) {
            Log.w(TAG, "Error : ", e);
        } catch (@NonNull NoSuchMethodException e) {
            Log.w(TAG, "Error : ", e);
        } catch (@NonNull SecurityException e) {
            Log.w(TAG, "Error : ", e);
        } catch (@NonNull ClassNotFoundException e) {
            Log.w(TAG, "Error : ", e);
        } catch (@NonNull IllegalAccessException e) {
            Log.w(TAG, "Error : ", e);
        }

        return result.toString();
    }

    private boolean isAuthorized(@Nullable Field key) {
        if (key == null || key.getName().startsWith("WIFI_AP")) {
            return false;
        }
        return true;
    }
}
