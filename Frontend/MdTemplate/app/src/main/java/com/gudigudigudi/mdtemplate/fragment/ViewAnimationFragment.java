package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.FragmentViewAnimationBinding;

import java.util.ArrayList;


public class ViewAnimationFragment extends BaseFragment {

    private FragmentViewAnimationBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentViewAnimationBinding.inflate(inflater, container, false);

        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("list item" + i);
        }
        binding.listView.setAdapter(new ArrayAdapter<String>(requireActivity(), android.R.layout.simple_list_item_1, list));

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_layout);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        binding.listView.setLayoutAnimation(controller);
        binding.listView.startAnimation(animation);

        return binding.getRoot();
    }
}