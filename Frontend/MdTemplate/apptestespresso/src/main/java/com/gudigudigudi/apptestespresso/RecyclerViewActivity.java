package com.gudigudigudi.apptestespresso;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gudigudigudi.commonlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    private static final int DATASET_COUNT = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        List<String> dataSet = new ArrayList<>(DATASET_COUNT);
        for (int i = 0; i < DATASET_COUNT; i++) {
            dataSet.add(getString(R.string.item_element_text) + i);
        }
        CustomAdapter adapter = new CustomAdapter(dataSet, getApplicationContext());
        recyclerView.setAdapter(adapter);
    }
}
