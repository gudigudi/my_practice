package com.gudigudigudi.mdtemplate.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.commonlib.base.BaseFragment;
import com.gudigudigudi.mdtemplate.R;

import java.util.ArrayList;
import java.util.Objects;


public class ViewAnimationFragment extends BaseFragment {

    private ListView mListView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_animation, container, false);

        mListView = view.findViewById(R.id.list_view);

        ArrayList list = new ArrayList();
        for (int i = 0; i < 10; i++) {
            list.add("list item" + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, list);
        mListView.setAdapter(adapter);

        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.animation_layout);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        mListView.setLayoutAnimation(controller);
        mListView.startAnimation(animation);

        return view;
    }
}