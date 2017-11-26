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

    private TextView mTextView;
    private Button mBtnSendEmptyMsg, mBtnSendMsgWhat0, mBtnSendMsgWhat1, mBtnSendDelayMsg;

    private TextView mTvTime;
    private Button mBtnRemoveTimer;

    private Handler mHandlerPostDelayed = new Handler();
    private Button mBtnHandlerPostDelayed;
    private long time = 0;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case -1:
                    mTextView.setText("receiver empty message");
                    break;
                case 0:
                    mTextView.setText("receiver message with what:0, message's content: " + msg.getData().getString("key"));
                    break;
                case 1:
                    mTextView.setText("receiver message with what:1, message's content: " + msg.getData().getString("key"));
                    break;
                case 2:
                    mTextView.setText("receiver delayed message, message's content: " + msg.getData().getString("key"));
                    break;
                case 3:
                    secondLeft--;
                    mTvTime.setText("" + secondLeft);
                    if (secondLeft > 0) {
                        Message message = mHandler.obtainMessage(3);
                        mHandler.sendMessageDelayed(message, 1000);
                    } else {
                        mTvTime.setText("倒计时结束");
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

        mTextView = findViewById(R.id.text_view);
        mBtnSendEmptyMsg = findViewById(R.id.btn_send_empty_msg);
        mBtnSendMsgWhat0 = findViewById(R.id.btn_send_msg_what0);
        mBtnSendMsgWhat1 = findViewById(R.id.btn_send_msg_what1);
        mBtnSendDelayMsg = findViewById(R.id.btn_send_delay_msg);
        mTvTime = findViewById(R.id.tv_time);
        mBtnRemoveTimer = findViewById(R.id.btn_remove_timer);
        mBtnHandlerPostDelayed = findViewById(R.id.btn_handler_postDelayed);

        mBtnSendEmptyMsg.setOnClickListener(this);
        mBtnSendMsgWhat0.setOnClickListener(this);
        mBtnSendMsgWhat1.setOnClickListener(this);
        mBtnSendDelayMsg.setOnClickListener(this);
        mTvTime.setOnClickListener(this);
        mBtnRemoveTimer.setOnClickListener(this);
        mBtnHandlerPostDelayed.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Message message = null;
        Bundle bundle = null;
        switch (view.getId()) {
            case R.id.btn_send_empty_msg:
                mHandler.sendEmptyMessage(-1);
                break;
            case R.id.btn_send_msg_what0:
                message = Message.obtain();
                message.what = 0;
                bundle = new Bundle();
                bundle.putString("key", "a message with what 0");
                message.setData(bundle);
                mHandler.sendMessage(message);
                break;
            case R.id.btn_send_msg_what1:
                message = Message.obtain();
                message.what = 1;
                bundle = new Bundle();
                bundle.putString("key", "a message with what 1");
                message.setData(bundle);
                mHandler.sendMessage(message);
                break;
            case R.id.btn_send_delay_msg:
                message = new Message();
                message.what = 2;
                bundle = new Bundle();
                bundle.putString("key", "a delayed message with what 2");
                message.setData(bundle);
                mHandler.sendMessageDelayed(message, 2000);
                break;
            case R.id.tv_time:
                if (secondLeft != 6) {
                    return;
                }

                message = mHandler.obtainMessage(3);
                mHandler.sendMessage(message);
                break;
            case R.id.btn_remove_timer:
                mHandler.removeMessages(3);
                break;
            case R.id.btn_handler_postDelayed:
                mHandlerPostDelayed.postDelayed(new MyRunnable(), 3000);
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
