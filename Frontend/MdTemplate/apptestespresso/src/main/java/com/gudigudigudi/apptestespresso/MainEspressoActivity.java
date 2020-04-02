package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.gudigudigudi.apptestespresso.databinding.ActivityMainEspressoBinding;

public class MainEspressoActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainEspressoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainEspressoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnChangeText.setOnClickListener(this);
        binding.btnChangeTextOfShowTextActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String text = binding.edittUserInput.getText().toString();

        switch (v.getId()) {
            case R.id.btn_change_text:
                binding.txtvToBeChanged.setText(text);
                break;
            case R.id.btn_change_text_of_ShowTextActivity:
                startActivity(ShowTextActivity.newStartIntent(this, text));
                break;
        }
    }
}
