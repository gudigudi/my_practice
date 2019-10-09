package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.model.Msg;
import com.gudigudigudi.mdtemplate.view.adapter.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class IMFragment extends BaseFragment {

    private List<Msg> mMsgList = new ArrayList<>();
    private EditText mETInput;
    private Button mBtnSend;
    private RecyclerView mRecyclerViewMsg;
    private MsgAdapter mMsgAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_im, container, false);

        mETInput = view.findViewById(R.id.input_text);
        mBtnSend = view.findViewById(R.id.send);

        mRecyclerViewMsg = view.findViewById(R.id.msg_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerViewMsg.setLayoutManager(layoutManager);
        initMsgs(); // 初始化消息数据
        mMsgAdapter = new MsgAdapter(mMsgList);
        mRecyclerViewMsg.setAdapter(mMsgAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBtnSend.setOnClickListener(view1 -> {
            String content = mETInput.getText().toString();
            if (!"".equals(content)) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                mMsgList.add(msg);
                // 当有新消息时，刷新RecyclerView中的显示
                mMsgAdapter.notifyItemInserted(mMsgList.size() - 1);
                // 将RecyclerView定位到最后一行
                mRecyclerViewMsg.scrollToPosition(mMsgList.size() - 1);
                // 清空输入框中的内容
                mETInput.setText("");
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
