package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

public class CardViewActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private CardView cardView;
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);

        cardView = findViewById(R.id.card_view);
        seekBar1 = findViewById(R.id.sb1);
        seekBar2 = findViewById(R.id.sb2);
        seekBar3 = findViewById(R.id.sb3);

        cardView.setOnClickListener(this);
        seekBar1.setOnSeekBarChangeListener(this);
        seekBar2.setOnSeekBarChangeListener(this);
        seekBar3.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_view:
                Toast.makeText(CardViewActivity.this, "You click CardView", Toast.LENGTH_SHORT).show();
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }

    @Override
    public void onProgressChanged(SeekBar bar, int i, boolean b) {
        switch (bar.getId()) {
            case R.id.sb1:
                cardView.setRadius(i);
                break;
            case R.id.sb2:
                cardView.setCardElevation(i);
                break;
            case R.id.sb3:
                cardView.setContentPadding(i, i, i, i);
                break;
            default:
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar bar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar bar) {

    }
}
