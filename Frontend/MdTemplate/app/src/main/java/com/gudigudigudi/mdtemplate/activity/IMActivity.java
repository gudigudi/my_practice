package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.adapter.MsgAdapter;
import com.gudigudigudi.mdtemplate.model.Msg;

import java.util.ArrayList;
import java.util.List;

public class IMActivity extends AppCompatActivity {

    private List<Msg> mMsgList = new ArrayList<>();
    private EditText mETInput;
    private Button mBtnSend;
    private RecyclerView mRecyclerViewMsg;
    private MsgAdapter mMsgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);

        initMsgs(); // 初始化消息数据

        mETInput = findViewById(R.id.input_text);
        mBtnSend = findViewById(R.id.send);

        mRecyclerViewMsg = findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewMsg.setLayoutManager(layoutManager);
        mMsgAdapter = new MsgAdapter(mMsgList);
        mRecyclerViewMsg.setAdapter(mMsgAdapter);

        mBtnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = mETInput.getText().toString();
                if (!content.equals("")) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    mMsgList.add(msg);
                    mMsgAdapter.notifyItemInserted(mMsgList.size() - 1); // 当有新消息时，刷新RecyclerView中的显示
                    mRecyclerViewMsg.scrollToPosition(mMsgList.size() - 1); // 将RecyclerView定位到最后一行
                    mETInput.setText(""); // 清空输入框中的内容
                }
            }
        });
    }

    private void initMsgs() {
        Msg msg1 = new Msg("Hello, guy.", Msg.TYPE_RECEIVED);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        Msg msg3 = new Msg("This is Tom. Nice talking to you.", Msg.TYPE_RECEIVED);

        mMsgList.add(msg1);
        mMsgList.add(msg2);
        mMsgList.add(msg3);
    }
}
