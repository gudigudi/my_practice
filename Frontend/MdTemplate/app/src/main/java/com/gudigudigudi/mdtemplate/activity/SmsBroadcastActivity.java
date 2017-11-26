package com.gudigudigudi.mdtemplate.activity;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.receiver.SmsBroadcastReceiver;
import com.orhanobut.logger.Logger;

public class SmsBroadcastActivity extends AppCompatActivity {

    private TextView mTextView;
    private SmsBroadcastReceiver mSmsBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_broadcast);

        mTextView = findViewById(R.id.text_view);
        mSmsBroadcastReceiver = new SmsBroadcastReceiver();

        init();

        registerReceiver(mSmsBroadcastReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        Logger.d("registerReceiver");
    }

    private void init() {
        mSmsBroadcastReceiver.setOnReceiveMessageListener(new SmsBroadcastReceiver.MessageListener() {
            @Override
            public void OnReceived(String message) {
                mTextView.setText(message);
            }
        });
    }
}
