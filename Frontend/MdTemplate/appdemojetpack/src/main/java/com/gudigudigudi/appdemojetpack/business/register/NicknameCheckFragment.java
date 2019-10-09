package com.gudigudigudi.appdemojetpack.business.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.commonlib.base.BaseFragment;

import lombok.Setter;


public class NicknameCheckFragment extends BaseFragment {

    @NonNull
    @Setter
    private NicknameValidateCallback nicknameValidateCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nickname_check, container, false);

        Button btnCheck = root.findViewById(R.id.btn_check);
        final EditText nickname = root.findViewById(R.id.et_nickname);
        btnCheck.setOnClickListener(view -> {
            nicknameValidateCallback.onNicknameValidateOk(nickname.getText().toString());
        });

        return root;
    }

    /**
     * 回调接口: 验证手机号
     */
    public interface NicknameValidateCallback {
        void onNicknameValidateOk(String phoneNumber);
    }

}
