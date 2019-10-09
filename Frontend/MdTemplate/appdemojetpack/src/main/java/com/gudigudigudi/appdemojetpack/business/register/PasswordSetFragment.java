package com.gudigudigudi.appdemojetpack.business.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.commonlib.base.BaseFragment;

import lombok.Setter;

public class PasswordSetFragment extends BaseFragment {

    @Setter
    private PasswordSetCallback passwordSetCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_phone_register, container, false);
        
        return root;
    }

    public void setParams(String phone, String nickname) {
        Bundle bundle = new Bundle();
        bundle.putString("phone", phone);
        bundle.putString("nickname", nickname);
        setArguments(bundle);
    }



    /**
     * 回调接口: 设置密码，注册流程结束
     */
    public interface PasswordSetCallback {
        void onRegisterOk(User user);
    }
}
