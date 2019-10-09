package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;


public class TransitionFragment extends BaseFragment {

    private Scene mScene1, mScene2;
    private Button mButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transition, container, false);

        mButton = view.findViewById(R.id.button);
        RelativeLayout sceneRoot = view.findViewById(R.id.scene_container);
        mScene1 = Scene.getSceneForLayout(sceneRoot, R.layout.fragment_transition, getActivity());
        mScene2 = Scene.getSceneForLayout(sceneRoot, R.layout.fragment_transition2, getActivity());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mButton.setOnClickListener(v -> {
            Transition transitionFade = TransitionInflater.from(getActivity()).inflateTransition(R.transition.fade);
            TransitionManager.go(mScene2, transitionFade);
        });
    }
}
