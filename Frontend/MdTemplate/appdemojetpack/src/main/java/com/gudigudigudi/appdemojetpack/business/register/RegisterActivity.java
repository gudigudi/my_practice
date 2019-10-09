package com.gudigudigudi.appdemojetpack.business.register;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.commonlib.base.BaseActivity;


/**
 * 注册流程由3个步骤组成:
 * 1. 验证手机号
 * 2. 设置昵称
 * 3. 设置密码
 */
public class RegisterActivity extends BaseActivity {
    String phoneNumber;
    String nickName;
    User mUser;

    PhoneRegisterFragment mPhoneRegisterFragment;
    NicknameCheckFragment mNicknameCheckFragment;
    PasswordSetFragment mPasswordSetFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mPhoneRegisterFragment = findOrCreateFragment(PhoneRegisterFragment.class);
        mNicknameCheckFragment = findOrCreateFragment(NicknameCheckFragment.class);
        mPasswordSetFragment = findOrCreateFragment(PasswordSetFragment.class);

        // 首次启动，把流程的第一个步骤代表的Fragment压栈
        if (savedInstanceState == null) {
            push(R.id.fragment_container, mPhoneRegisterFragment);
        }

        // 负责验证完手机号后启动设置昵称
        mPhoneRegisterFragment.setPhoneValidateCallback(s -> {
            RegisterActivity.this.phoneNumber = s;
            push(R.id.fragment_container, mNicknameCheckFragment);
        });

        // 设置完昵称后启动设置密码
        mNicknameCheckFragment.setNicknameValidateCallback(s -> {
            mPasswordSetFragment.setParams(s, nickName);
            push(R.id.fragment_container, mPasswordSetFragment);
        });

        // 设置完密码后，注册流程结束
        mPasswordSetFragment.setPasswordSetCallback(user -> {
            mUser = user;
            Intent intent = new Intent();
            intent.putExtra("user", mUser.toString());
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("phoneNumber", phoneNumber);
        outState.putString("nickName", nickName);
        outState.putSerializable("user", mUser.toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        phoneNumber = savedInstanceState.getString("phoneNumber");
        nickName = savedInstanceState.getString("nickName");
        mUser = (User) savedInstanceState.getSerializable("user");
    }
}
