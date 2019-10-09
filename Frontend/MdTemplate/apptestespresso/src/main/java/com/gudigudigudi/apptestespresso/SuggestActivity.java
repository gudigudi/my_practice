package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.gudigudigudi.commonlib.base.BaseActivity;

public class SuggestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest);

        setUpAutoCompleteTextView();
    }

    private void setUpAutoCompleteTextView() {
        String[] completions = getResources().getStringArray(R.array.bodies_of_water);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, completions);

        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.auto_complete_text_view);
        autoCompleteTextView.setAdapter(adapter);
    }
}
