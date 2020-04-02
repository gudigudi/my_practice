package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.snackbar.Snackbar;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.databinding.ActivityMaterialDesignBinding;
import com.gudigudigudi.mdtemplate.model.Fruit;
import com.gudigudigudi.mdtemplate.view.adapter.FruitRCAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialDesignActivity extends BaseActivity {
    private static final String TAG = "MaterialDesignActivity";

    private Fruit[] fruits = {
            new Fruit("Apple", R.drawable.apple),
            new Fruit("Banana", R.drawable.banana),
            new Fruit("Orange", R.drawable.orange),
            new Fruit("Watermelon", R.drawable.watermellon),
            new Fruit("Pear", R.drawable.pear),
            new Fruit("Grape", R.drawable.grapes),
            new Fruit("Pineapple", R.drawable.pineapple),
            new Fruit("Strawberry", R.drawable.strawberry),
            new Fruit("Cherry", R.drawable.cherry),
            new Fruit("Mango", R.drawable.mango),
    };

    private List<Fruit> fruitList = new ArrayList<>();
    private FruitRCAdapter adapter;

    private ActivityMaterialDesignBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMaterialDesignBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_create);
        }

        binding.navView.setCheckedItem(R.id.nav_call);
        binding.navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_call:
                    break;
                case R.id.nav_friends:
                    break;
                case R.id.nav_location:
                    break;
                case R.id.nav_mail:
                    break;
                case R.id.nav_task:
                    break;
                default:
                    Log.d(TAG, "Unknown navigation menu item is clicked.");
                    break;
            }

            binding.drawerLayout.closeDrawers();
            return true;
        });

        binding.fab.setOnClickListener(view -> Snackbar.make(view, "Snackbar is clicked", Snackbar.LENGTH_SHORT)
                .setAction("OK", view1 -> ToastUtils.showShort("FAB is clicked:OK")).show());

        initFruits();

        binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new FruitRCAdapter(fruitList);
        binding.recyclerView.setAdapter(adapter);

        binding.swipeRefleshLayout.setColorSchemeColors(ColorUtils.getColor(R.color.colorPrimary));
        binding.swipeRefleshLayout.setOnRefreshListener(this::refreshFruits);
    }

    private void refreshFruits() {
        new Handler().post(() -> MaterialDesignActivity.this.runOnUiThread(() -> {
            MaterialDesignActivity.this.initFruits();
            adapter.notifyDataSetChanged();
            binding.swipeRefleshLayout.setRefreshing(false);
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                log.debug(LogTag.LOG_VIEW_IS_CLICKED, "backup");
                break;
            case R.id.delete:
                log.debug(LogTag.LOG_VIEW_IS_CLICKED, "delete");
                break;
            case R.id.settings:
                log.debug(LogTag.LOG_VIEW_IS_CLICKED, "settings");
                break;
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                log.debug(LogTag.LOG_UNKNOWN_MENU_ITEM_IS_CLICKED);
        }
        return true;
    }

    private void initFruits() {
        fruitList.clear();
        for (int i = 0; i < 50; i++) {
            fruitList.add(fruits[new Random().nextInt(fruits.length)]);
        }
    }
}
