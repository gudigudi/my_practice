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

public class PhoneRegisterFragment extends BaseFragment {

    public static final String TAG = "PhoneRegisterFragment";

    @NonNull
    @Setter
    PhoneValidateCallback phoneValidateCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_phone_register, container, false);

        Button button = root.findViewById(R.id.btn_action);
        final EditText input = root.findViewById(R.id.et_input);
        button.setOnClickListener(view -> phoneValidateCallback.onPhoneValidateOk(input.getText().toString()));

        return root;
    }

    /**
     * 回调接口: 验证手机号
     */
    public interface PhoneValidateCallback {
        void onPhoneValidateOk(String phoneNumber);
    }
}
