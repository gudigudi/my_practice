package com.gudigudigudi.appdemojetpack.business.userProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.appdemojetpack.databinding.FragmentUserProfileBinding;
import com.gudigudigudi.commonlib.base.BaseFragment;

public class UserProfileFragment extends BaseFragment {
    private UserProfileViewModel viewModel;

    private FragmentUserProfileBinding binding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(UserProfileViewModel.class);
        viewModel.getUser().observe(this, user -> {
            // 当数据改变时，更新UI
            binding.userName.setText(user.name);
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_profile, container, false);

        binding.setUser(new User(1, "22", "333"));

        binding.btn.setOnClickListener(v -> {
            viewModel.setUserName(binding.getUser().name);
        });

        return binding.getRoot();
    }
}
