package com.gudigudigudi.mdtemplate.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gudigudigudi.mdtemplate.base.BaseApplication;

/**
 * Created by gu on 12/9/17.
 */


public class SharedPreferenceUtil {
    private static final String TAG = "SharedPreferenceUtil";

    private static final String APP_SP = "app_sp";

    private static SharedPreferenceUtil instance = new SharedPreferenceUtil();
    private static SharedPreferences mSp = null;

    public static SharedPreferenceUtil getInstance() {
        if (mSp == null) {
            mSp = BaseApplication.getContext().getSharedPreferences(APP_SP, Context.MODE_PRIVATE);
        }
        return instance;
    }

    private SharedPreferenceUtil() {
    }

    /**
     * save data.
     *
     * @param key
     * @param value
     */
    public void save(String key, Object value) {
        if (value == null) {
            Log.e(TAG, "Fail to save: value==null");
            return;
        }
        if (value instanceof String) {
            mSp.edit().putString(key, (String) value).commit();
        } else if (value instanceof Boolean) {
            mSp.edit().putBoolean(key, (Boolean) value).commit();
        } else if (value instanceof Integer) {
            mSp.edit().putInt(key, (Integer) value).commit();
        }
    }

    public String getString(String key, String defValue) {
        return mSp.getString(key, defValue);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mSp.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return mSp.getInt(key, defValue);
    }

    public void clearAll() {
        mSp.edit().clear().commit();
    }
}
