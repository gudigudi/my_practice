package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.gudigudigudi.commonlib.base.BaseActivity;
import com.gudigudigudi.commonlib.constants.LogTag;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.model.Fruit;
import com.gudigudigudi.mdtemplate.view.adapter.FruitRCAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MaterialDesignActivity extends BaseActivity {
    private static final String TAG = "MaterialDesignActivity";

    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FloatingActionButton mFloatingActionButton;
    private SwipeRefreshLayout mSwipeRefreshLayout;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_create);
        }

        mNavigationView = findViewById(R.id.nav_view);
        mNavigationView.setCheckedItem(R.id.nav_call);
        mNavigationView.setNavigationItemSelectedListener(item -> {
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

            mDrawerLayout.closeDrawers();
            return true;
        });

        mFloatingActionButton = findViewById(R.id.fab);
        mFloatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Snackbar is clicked", Snackbar.LENGTH_SHORT)
                .setAction("OK", view1 -> ToastUtils.showShort("FAB is clicked:OK")).show());

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitRCAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        mSwipeRefreshLayout = findViewById(R.id.swipe_reflesh);
        mSwipeRefreshLayout.setColorSchemeColors(ColorUtils.getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(this::refreshFruits);
    }

    private void refreshFruits() {
        new Handler().post(() -> MaterialDesignActivity.this.runOnUiThread(() -> {
            MaterialDesignActivity.this.initFruits();
            adapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
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
                mDrawerLayout.openDrawer(GravityCompat.START);
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
