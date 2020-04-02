package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.gudigudigudi.apptestespresso.databinding.ActivitySuggestBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

public class SuggestActivity extends BaseActivity {

    private ActivitySuggestBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySuggestBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setUpAutoCompleteTextView();
    }

    private void setUpAutoCompleteTextView() {
        String[] completions = getResources().getStringArray(R.array.bodies_of_water);
        binding.autoCompleteTextView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, completions));
    }
}
