package com.gudigudigudi.mdtemplate.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;

public class BroadcastActivity extends AppCompatActivity {

    private static final String TAG = "BroadcastActivity";

    private IntentFilter mIntentFilter;
    private NetworkChangeReceiver mNetworkChangeReceiver;

    private IntentFilter mIntentFilterLocal;
    private LocalReceiver mLocalReceiver;
    private LocalBroadcastManager mLocalBroadcastManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        mNetworkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(mNetworkChangeReceiver, mIntentFilter);

        Button button = findViewById(R.id.replace);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String intentStr = getResources().getString(R.string.CUSTOM_BROADCAST);
                Log.d(TAG, "send broadcast with intent " + intentStr);
                Intent intent = new Intent(intentStr);
                sendBroadcast(intent);
            }
        });

        mLocalBroadcastManager = LocalBroadcastManager.getInstance(this);
        mIntentFilterLocal = new IntentFilter();
        mIntentFilterLocal.addAction(getString(R.string.LOCAL_BROADCAST));
        mLocalReceiver = new LocalReceiver();
        mLocalBroadcastManager.registerReceiver(mLocalReceiver, mIntentFilterLocal);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getString(R.string.LOCAL_BROADCAST));
                mLocalBroadcastManager.sendBroadcast(intent); // send local broadcast.
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mNetworkChangeReceiver);
        mLocalBroadcastManager.unregisterReceiver(mLocalReceiver);
    }

    /**
     * 网络检测广播接收器
     */
    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                Toast.makeText(context, "network is available", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "network is unavailable", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 本地广播接收器
     */
    class LocalReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "received local broadcast", Toast.LENGTH_SHORT).show();
        }
    }
}
