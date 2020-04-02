package com.gudigudigudi.apptestespresso;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.gudigudigudi.apptestespresso.databinding.ActivityRecyclerViewBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    private static final int DATASET_COUNT = 50;

    private ActivityRecyclerViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        List<String> dataSet = new ArrayList<>(DATASET_COUNT);
        for (int i = 0; i < DATASET_COUNT; i++) {
            dataSet.add(getString(R.string.item_element_text) + i);
        }
        binding.recyclerView.setAdapter(new CustomAdapter(dataSet, getApplicationContext()));
    }
}
