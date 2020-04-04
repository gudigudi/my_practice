package com.gudigudigudi.appdemojetpack.business.userProfile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.gudigudigudi.appdemojetpack.R;
import com.gudigudigudi.appdemojetpack.databinding.FragmentUserProfileBinding;
import com.gudigudigudi.commonlib.base.BaseFragment;

public class UserProfileFragment extends BaseFragment {
    private UserProfileViewModel viewModel;

    private FragmentUserProfileBinding binding;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(UserProfileViewModel.class);
        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
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
