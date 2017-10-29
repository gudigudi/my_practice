package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

public class HandlerActivity extends AppCompatActivity implements View.OnClickListener {

    private int secondLeft = 6;

    private TextView textView;
    private Button btn_send_empty_msg, btn_send_msg_what0, btn_send_msg_what1, btn_send_delay_msg;

    private TextView tv_time;
    private Button btn_remove_timer;

    private Handler handler_postDelayed = new Handler();
    private Button btn_handler_postDelayed;
    private long time = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    textView.setText("receiver empty message");
                    break;
                case 0:
                    textView.setText("receiver message with what:0, message's content: " + msg.getData().getString("key"));
                    break;
                case 1:
                    textView.setText("receiver message with what:1, message's content: " + msg.getData().getString("key"));
                    break;
                case 2:
                    textView.setText("receiver delayed message, message's content: " + msg.getData().getString("key"));
                    break;
                case 3:
                    secondLeft--;
                    tv_time.setText("" + secondLeft);
                    if (secondLeft > 0) {
                        Message message = handler.obtainMessage(3);
                        handler.sendMessageDelayed(message, 1000);
                    } else {
                        tv_time.setText("倒计时结束");
                        secondLeft = 6;
                    }
                default:
            }
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        textView = findViewById(R.id.text_view);
        btn_send_empty_msg = findViewById(R.id.btn_send_empty_msg);
        btn_send_msg_what0 = findViewById(R.id.btn_send_msg_what0);
        btn_send_msg_what1 = findViewById(R.id.btn_send_msg_what1);
        btn_send_delay_msg = findViewById(R.id.btn_send_delay_msg);
        tv_time = findViewById(R.id.tv_time);
        btn_remove_timer = findViewById(R.id.btn_remove_timer);
        btn_handler_postDelayed = findViewById(R.id.btn_handler_postDelayed);

        btn_send_empty_msg.setOnClickListener(this);
        btn_send_msg_what0.setOnClickListener(this);
        btn_send_msg_what1.setOnClickListener(this);
        btn_send_delay_msg.setOnClickListener(this);
        tv_time.setOnClickListener(this);
        btn_remove_timer.setOnClickListener(this);
        btn_handler_postDelayed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Message message = null;
        Bundle bundle = null;
        switch (view.getId()) {
            case R.id.btn_send_empty_msg:
                handler.sendEmptyMessage(-1);
                break;
            case R.id.btn_send_msg_what0:
                message = Message.obtain();
                message.what = 0;
                bundle = new Bundle();
                bundle.putString("key", "a message with what 0");
                message.setData(bundle);
                handler.sendMessage(message);
                break;
            case R.id.btn_send_msg_what1:
                message = Message.obtain();
                message.what = 1;
                bundle = new Bundle();
                bundle.putString("key", "a message with what 1");
                message.setData(bundle);
                handler.sendMessage(message);
                break;
            case R.id.btn_send_delay_msg:
                message = new Message();
                message.what = 2;
                bundle = new Bundle();
                bundle.putString("key", "a delayed message with what 2");
                message.setData(bundle);
                handler.sendMessageDelayed(message, 2000);
                break;
            case R.id.tv_time:
                if (secondLeft != 6) {
                    return;
                }

                message = handler.obtainMessage(3);
                handler.sendMessage(message);
                break;
            case R.id.btn_remove_timer:
                handler.removeMessages(3);
                break;
            case R.id.btn_handler_postDelayed:
                handler_postDelayed.postDelayed(new MyRunnable(), 3000);
                time = System.currentTimeMillis();
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }

    private class MyRunnable implements Runnable {

        @Override
        public void run() {
            Logger.d("run: time delay = " + (System.currentTimeMillis() - time));
        }
    }
}
