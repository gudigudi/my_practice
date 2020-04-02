package com.gudigudigudi.apptestespresso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import androidx.annotation.VisibleForTesting;

import com.gudigudigudi.apptestespresso.databinding.ActivityLongListBinding;
import com.gudigudigudi.commonlib.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongListActivity extends BaseActivity {

    @VisibleForTesting
    protected static final String ROW_TEXT = "ROW_TEXT";

    @VisibleForTesting
    protected static final String ROW_ENABLED = "ROW_ENABLED";

    @VisibleForTesting
    protected static final int NUMBER_OF_ITEMS = 100;

    @VisibleForTesting
    protected static final String ITEM_TEXT_FORMAT = "item: %d";

    private List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

    private LayoutInflater layoutInflater;

    private ActivityLongListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        layoutInflater = getLayoutInflater();
        binding = ActivityLongListBinding.inflate(layoutInflater);
        setContentView(binding.getRoot());

        populateData();

        String[] from = new String[]{ROW_TEXT, ROW_ENABLED};
        int[] to = new int[]{R.id.rowContentTextView, R.id.rowToggleButton};


        ListAdapter adapter = new LongListAdapter(from, to);
        binding.list.setAdapter(adapter);
    }

    @VisibleForTesting
    protected static Map<String, Object> makeItem(int forRow) {
        Map<String, Object> dataRow = new HashMap<>();
        dataRow.put(ROW_TEXT, String.format(ITEM_TEXT_FORMAT, forRow));
        dataRow.put(ROW_ENABLED, forRow == 1);
        return dataRow;
    }

    private void populateData() {
        for (int i = 0; i < NUMBER_OF_ITEMS; i++) {
            data.add(makeItem(i));
        }
    }

    private class LongListAdapter extends SimpleAdapter {

        public LongListAdapter(String[] from, int[] to) {
            super(LongListActivity.this, LongListActivity.this.data, R.layout.item_list, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (null == convertView) {
                convertView = layoutInflater.inflate(R.layout.item_list, null);
            }

            convertView.setOnClickListener(v -> {
                binding.selectionRowValue.setText(String.valueOf(position));
            });

            return super.getView(position, convertView, parent);
        }
    }
}
