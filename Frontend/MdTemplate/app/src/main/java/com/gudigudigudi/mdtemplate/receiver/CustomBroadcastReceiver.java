package com.gudigudigudi.mdtemplate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CustomBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "CustomBroadcastReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "received in " + TAG, Toast.LENGTH_LONG).show();
    }
}
