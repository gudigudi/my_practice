package com.gudigudigudi.mdtemplate.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivityDatabindingBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DatabindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_databinding);
//        setContentView(R.layout.activity_databinding);


        // model binding
        Swordsman swordsman = new Swordsman("The Name", "S");
        binding.setSwordsman(swordsman);

        // event binding
        binding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DatabindingActivity.this, "Yelp", Toast.LENGTH_SHORT).show();
            }
        });

        // data-structure binding
        binding.setName("GG");
        binding.setAge(22);
        binding.setMan(true);

        ArrayList list = new ArrayList();
        list.add("0");
        list.add("1");
        binding.setList(list);

        Map map = new HashMap();
        map.put("age", "30");
        binding.setMap(map);

        String[] arrays = {"ones", "twos"};
        binding.setArrays(arrays);
    }
}
