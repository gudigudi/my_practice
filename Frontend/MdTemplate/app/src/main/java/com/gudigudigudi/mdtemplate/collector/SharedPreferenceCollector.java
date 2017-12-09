package com.gudigudigudi.mdtemplate.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by gu on 12/9/17.
 */

public class SharedPreferenceCollector {

    private final static String TAG = "SharedPreferenceCollector";

    private final Context mContext;
    private String[] mSharedPrefIds;

    public SharedPreferenceCollector(Context context, String[] sharedPrefIds) {
        mContext = context;
        mSharedPrefIds = sharedPrefIds;
    }

    @NonNull
    public String collect() {
        final StringBuilder result = new StringBuilder();

        final Map<String, SharedPreferences> sharedPrefs = new TreeMap<String, SharedPreferences>();
        sharedPrefs.put("default", PreferenceManager.getDefaultSharedPreferences(mContext));

        if (mSharedPrefIds != null) {
            for (final String sharedPrefId : mSharedPrefIds) {
                sharedPrefs.put(sharedPrefId, mContext.getSharedPreferences(sharedPrefId, Context.MODE_PRIVATE));
            }
        }

        for (Map.Entry<String, SharedPreferences> entry : sharedPrefs.entrySet()) {
            final String sharedPrefId = entry.getKey();
            final SharedPreferences prefs = entry.getValue();
            final Map<String, ?> prefEntries = prefs.getAll();

            if (prefEntries.isEmpty()) {
                result.append(sharedPrefId).append('=').append("empty\n");
                continue;
            }

            for (final Map.Entry<String, ?> prefEntry : prefEntries.entrySet()) {
                final Object prefValue = prefEntry.getValue();
                result.append(sharedPrefId).append('.').append(prefEntry.getKey()).append('=');
                result.append(prefValue == null ? "null" : prefValue.toString());
                result.append('\n');
            }
            result.append('\n');
        }
        return result.toString();
    }

}
