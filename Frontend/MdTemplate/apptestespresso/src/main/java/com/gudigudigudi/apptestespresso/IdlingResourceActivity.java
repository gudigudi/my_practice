package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.IdlingResource;

public class IdlingResourceActivity extends AppCompatActivity implements View.OnClickListener, MessageDelayer.DelayerCallback {

    private TextView mTextView;
    private EditText mEditText;

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_espresso);

        findViewById(R.id.changeTextBtn).setOnClickListener(this);

        mTextView = findViewById(R.id.textToBeChanged);
        mEditText = findViewById(R.id.editTextUserInput);
    }

    @Override
    public void onClick(View v) {
        final String text = mEditText.getText().toString();

        if (v.getId() == R.id.changeTextBtn) {
            mTextView.setText(R.string.app_name);
            MessageDelayer.processMessage(text, this, mIdlingResource);
        }
    }

    @Override
    public void onDone(String text) {
        mTextView.setText(text);
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
