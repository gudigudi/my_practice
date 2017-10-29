package com.gudigudigudi.mdtemplate.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.adapter.FruitRCAdapter;
import com.gudigudigudi.mdtemplate.model.Fruit;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MaterialDesignActivity extends AppCompatActivity {
    private static final String TAG = "MaterialDesignActivity";

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    private SwipeRefreshLayout swipeRefreshLayout;

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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_create);
        }

        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_call);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

                drawerLayout.closeDrawers();
                return true;
            }
        });

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Snackbar is clicked", Snackbar.LENGTH_SHORT)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MaterialDesignActivity.this, "FAB is clicked:OK", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
            }
        });

        initFruits();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new FruitRCAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout = findViewById(R.id.swipe_reflesh);
        swipeRefreshLayout.setColorSchemeColors(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }

    private void refreshFruits() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
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
                Logger.d(LogUtil.LOG_VIEW_IS_CLICKED, "backup");
                break;
            case R.id.delete:
                Logger.d(LogUtil.LOG_VIEW_IS_CLICKED, "delete");
                break;
            case R.id.settings:
                Logger.d(LogUtil.LOG_VIEW_IS_CLICKED, "settings");
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_MENU_ITEM_IS_CLICKED);
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
