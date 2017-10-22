package com.gudigudigudi.mdtemplate.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;

import com.orhanobut.logger.Logger;

public class SmsBroadcastReceiver extends BroadcastReceiver {

    private MessageListener messageListener;

    public void setOnReceiveMessageListener(MessageListener messageListener) {
        this.messageListener = messageListener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (SmsMessage sm : smsMessages) {
            String content = sm.getMessageBody();
            Logger.d(content);
            messageListener.OnReceived(content);
        }
    }

    /**
     * callback listener.
     */
    public interface MessageListener {
        void OnReceived(String message);
    }
}
