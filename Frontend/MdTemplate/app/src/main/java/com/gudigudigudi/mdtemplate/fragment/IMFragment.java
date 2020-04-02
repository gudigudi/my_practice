package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.databinding.FragmentImBinding;
import com.gudigudigudi.mdtemplate.model.Msg;
import com.gudigudigudi.mdtemplate.view.adapter.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class IMFragment extends BaseFragment {

    private List<Msg> mMsgList = new ArrayList<>();
    private MsgAdapter mMsgAdapter;

    private FragmentImBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentImBinding.inflate(inflater, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        binding.recyclerViewMsg.setLayoutManager(layoutManager);
        initMsgs(); // 初始化消息数据
        mMsgAdapter = new MsgAdapter(mMsgList);
        binding.recyclerViewMsg.setAdapter(mMsgAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnSend.setOnClickListener(view1 -> {
            String content = binding.etInputText.getText().toString();
            if (!"".equals(content)) {
                Msg msg = new Msg(content, Msg.TYPE_SENT);
                mMsgList.add(msg);
                // 当有新消息时，刷新RecyclerView中的显示
                mMsgAdapter.notifyItemInserted(mMsgList.size() - 1);
                // 将RecyclerView定位到最后一行
                binding.recyclerViewMsg.scrollToPosition(mMsgList.size() - 1);
                // 清空输入框中的内容
                binding.etInputText.setText("");
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
