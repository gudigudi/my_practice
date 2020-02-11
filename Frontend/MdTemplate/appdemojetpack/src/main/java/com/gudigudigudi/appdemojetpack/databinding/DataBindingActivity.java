package com.gudigudigudi.appdemojetpack.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.gudigudigudi.appdemojetpack.R;

public class DataBindingActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityDataBindingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        binding.setTextStr("set data");
        binding.setOnClickListener(this);

        User user = new User("java", 10);

        binding.setUserFirst(user);

        View root = binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        binding.txt.setText("click data");
    }
}
