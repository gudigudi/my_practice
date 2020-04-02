package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import com.gudigudigudi.apptestespresso.databinding.ActivityMatcherBinding;

import java.util.Arrays;
import java.util.List;

public class MatcherActivity extends AppCompatActivity implements View.OnClickListener {

    @VisibleForTesting
    public static final List<String> COFFEE_PREPARATIONS =
            Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew");

    @VisibleForTesting
    public static final String VALID_ENDING = "coffee";

    private ActivityMatcherBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMatcherBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            final String inputText = binding.editText.getText().toString();

            showResult(validateText(inputText));
        }
    }

    private void showResult(boolean isValidResult) {
        binding.inputValidationSuccess.setVisibility(isValidResult ? View.VISIBLE : View.GONE);
        binding.inputValidationError.setVisibility(isValidResult ? View.GONE : View.VISIBLE);
    }

    private static boolean validateText(String inputText) {
        // Every input ending in VALID_ENDING will return true.
        if (inputText.toLowerCase().endsWith(VALID_ENDING)) {
            return true;
        }

        // Check if the string is in the list.
        for (String preparation : COFFEE_PREPARATIONS) {
            if (preparation.equalsIgnoreCase(inputText)) {
                return true;
            }
        }
        return false;
    }
}
