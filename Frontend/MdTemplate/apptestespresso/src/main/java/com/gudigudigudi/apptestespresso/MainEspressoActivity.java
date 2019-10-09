package com.gudigudigudi.apptestespresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainEspressoActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_espresso);

        findViewById(R.id.changeTextBtn).setOnClickListener(this);
        findViewById(R.id.activityChangeTextBtn).setOnClickListener(this);

        mTextView = findViewById(R.id.textToBeChanged);
        mEditText = findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View v) {
        final String text = mEditText.getText().toString();

        final int changeTextBtnId = R.id.changeTextBtn;
        final int activityChangeTextBtnId = R.id.activityChangeTextBtn;

        switch (v.getId()) {
            case changeTextBtnId:
                mTextView.setText(text);
                break;
            case activityChangeTextBtnId:
                Intent intent = ShowTextActivity.newStartIntent(this, text);
                startActivity(intent);
                break;
        }
    }
}
