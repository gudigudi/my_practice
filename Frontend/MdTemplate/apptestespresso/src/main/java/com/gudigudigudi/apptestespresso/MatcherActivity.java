package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class MatcherActivity extends AppCompatActivity implements View.OnClickListener {

    @VisibleForTesting
    public static final List<String> COFFEE_PREPARATIONS =
            Arrays.asList("Espresso", "Latte", "Mocha", "Café con leche", "Cold brew");

    @VisibleForTesting
    public static final String VALID_ENDING = "coffee";

    private EditText mInputText;
    private View mSuccessView;
    private View mErrorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matcher);

        findViewById(R.id.button).setOnClickListener(this);

        mInputText = findViewById(R.id.editText);
        mSuccessView = findViewById(R.id.inputValidationSuccess);
        mErrorView = findViewById(R.id.inputValidationError);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            final String inputText = mInputText.getText().toString();

            showResult(validateText(inputText));
        }
    }

    private void showResult(boolean isValidResult) {
        mSuccessView.setVisibility(isValidResult ? View.VISIBLE : View.GONE);
        mErrorView.setVisibility(isValidResult ? View.GONE : View.VISIBLE);
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
