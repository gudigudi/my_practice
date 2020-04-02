package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivityMainBinding;
import com.gudigudigudi.mdtemplate.model.Fruit;
import com.gudigudigudi.mdtemplate.view.adapter.FruitRCAdapter;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private List<Fruit> fruitList;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initFruits(); // init data of fruits

        binding.recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        binding.recyclerView.setAdapter(new FruitRCAdapter(fruitList));
    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.apple);
        Fruit banana = new Fruit("Banana", R.drawable.banana);
        Fruit orange = new Fruit("Orange", R.drawable.orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.watermellon);
        Fruit pear = new Fruit("Pear", R.drawable.pear);
        Fruit grape = new Fruit("Grape", R.drawable.grapes);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.cherry);
        Fruit mango = new Fruit("Mango", R.drawable.mango);

        fruitList = new ArrayList<>();
        fruitList.add(apple);
        fruitList.add(banana);
        fruitList.add(orange);
        fruitList.add(watermelon);
        fruitList.add(pear);
        fruitList.add(grape);
        fruitList.add(pineapple);
        fruitList.add(strawberry);
        fruitList.add(cherry);
        fruitList.add(mango);
    }
}
