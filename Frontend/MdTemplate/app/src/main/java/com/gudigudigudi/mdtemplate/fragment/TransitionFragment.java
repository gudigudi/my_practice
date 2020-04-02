package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.FragmentTransitionBinding;


public class TransitionFragment extends BaseFragment {

    private Scene mScene1, mScene2;

    private FragmentTransitionBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTransitionBinding.inflate(inflater, container, false);

        mScene1 = Scene.getSceneForLayout(binding.sceneContainer, R.layout.fragment_transition, getActivity());
        mScene2 = Scene.getSceneForLayout(binding.sceneContainer, R.layout.fragment_transition2, getActivity());

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.button.setOnClickListener(v -> {
            Transition transitionFade = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);
            TransitionManager.go(mScene2, transitionFade);
        });
    }
}
