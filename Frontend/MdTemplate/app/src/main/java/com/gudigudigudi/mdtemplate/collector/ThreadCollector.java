package com.gudigudigudi.mdtemplate.collector;

import android.support.annotation.NonNull;

import javax.annotation.Nullable;

/**
 * Created by gu on 12/9/17.
 */

public class ThreadCollector {

    @NonNull
    public static String collect(@Nullable Thread thread) {
        StringBuilder result = new StringBuilder();

        if (thread != null) {
            result.append("id=").append(thread.getId()).append("\n");
            result.append("name=").append(thread.getName()).append("\n");
            result.append("priority=").append(thread.getPriority()).append("\n");

            if (thread.getThreadGroup() != null) {
                result.append("groupName=").append(thread.getThreadGroup().getName()).append("\n");
            }
        }
        return result.toString();
    }
}
