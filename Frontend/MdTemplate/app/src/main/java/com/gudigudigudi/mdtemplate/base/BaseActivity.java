package com.gudigudigudi.mdtemplate.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.gudigudigudi.mdtemplate.ActivityCollector;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.activity.LoginActivity;
import com.orhanobut.logger.Logger;

public class BaseActivity extends AppCompatActivity {

    private final static String TAG = "BaseActivity";

    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Logger.d(TAG, "Current Activity is " + getClass().getSimpleName());
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (receiver != null) {
            unregisterReceiver(receiver);
            receiver = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getString(R.string.FORCE_OFFLINE_BROADCAST));
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        ActivityCollector.removeActivity(this);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Warning")
                    .setMessage("You are forced to be offline. Please try to login again.")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface anInterface, int i) {
                            ActivityCollector.finishAll();
                            Intent intent = new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                        }
                    }).show();
        }
    }
}
