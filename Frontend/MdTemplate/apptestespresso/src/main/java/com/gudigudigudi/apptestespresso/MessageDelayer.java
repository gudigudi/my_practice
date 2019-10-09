package com.gudigudigudi.apptestespresso;

import android.os.Handler;

import androidx.annotation.Nullable;

public class MessageDelayer {

    private static final int DELAY_MILLIS = 3000;

    /**
     * Takes a String and returns it after {@link #DELAY_MILLIS} via a {@link DelayerCallback}.
     *
     * @param message  the String that will be returned via the callback
     * @param callback used to notify the caller asynchronously
     */
    static void processMessage(final String message, final DelayerCallback callback,
                               @Nullable final SimpleIdlingResource idlingResource) {
        // The IdlingResource is null in production.
        if (idlingResource != null) {
            idlingResource.setIdleState(false);
        }

        // Delay the execution, return message via callback.
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            if (callback != null) {
                callback.onDone(message);
                if (idlingResource != null) {
                    idlingResource.setIdleState(true);
                }
            }
        }, DELAY_MILLIS);
    }

    interface DelayerCallback {
        void onDone(String text);
    }
}
