package com.gudigudigudi.mdtemplate.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.receiver.SmsBroadcastReceiver;
import com.orhanobut.logger.Logger;

public class SmsBroadcastActivity extends AppCompatActivity {

    private TextView textView;
    private SmsBroadcastReceiver smsBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_broadcast);

        textView = findViewById(R.id.text_view);
        smsBroadcastReceiver = new SmsBroadcastReceiver();

        init();

        registerReceiver(smsBroadcastReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        Logger.d("registerReceiver");
    }

    private void init() {
        smsBroadcastReceiver.setOnReceiveMessageListener(new SmsBroadcastReceiver.MessageListener() {
            @Override
            public void OnReceived(String message) {
                textView.setText(message);
            }
        });
    }
}
