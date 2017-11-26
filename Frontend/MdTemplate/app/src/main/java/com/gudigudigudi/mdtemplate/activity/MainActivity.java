package com.gudigudigudi.mdtemplate.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.adapter.FruitRCAdapter;
import com.gudigudigudi.mdtemplate.base.BaseActivity;
import com.gudigudigudi.mdtemplate.model.Fruit;
import com.gudigudigudi.mdtemplate.util.LogUtil;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button mBtn1;
    private Button mBtnGotoFirstactivity;
    private EditText mEditText;
    private Button mBtnToastEdittext;
    private ImageView mImageView;
    private Button mBtnChangeImageview;
    private Button mBtnToggleAlertdialog;
    private Button mBtnForceOffline;

    private String[] data = {
            "Apple", "Banana", "Orange", "Watermelon", "Pear",
            "Grape", "Pineapple", "Strawberry", "Cherry", "Mango"
    };

    private List<Fruit> fruitList = new ArrayList<>();


    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            String tmpData = savedInstanceState.getString("data_key");
            Log.d(TAG, "tmpData : " + tmpData);
        }

        mBtn1 = findViewById(R.id.button1);
        mBtnGotoFirstactivity = findViewById(R.id.button_goto_imactivity);
        mEditText = findViewById(R.id.edit_text);
        mBtnToastEdittext = findViewById(R.id.btn_toast_edittext);
        mImageView = findViewById(R.id.image_view);
        mBtnChangeImageview = findViewById(R.id.btn_change_imageview);
        mBtnToggleAlertdialog = findViewById(R.id.btn_toggle_alertdialog);
        mBtnForceOffline = findViewById(R.id.force_offline);

        mBtn1.setText(stringFromJNI());

        mBtn1.setOnClickListener(this);
        mBtnGotoFirstactivity.setOnClickListener(this);
        mBtnToastEdittext.setOnClickListener(this);
        mBtnChangeImageview.setOnClickListener(this);
        mBtnToggleAlertdialog.setOnClickListener(this);
        mBtnForceOffline.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            actionBar.hide();
        }

        initFruits(); // init data of fruits

//        FruitAdapter mAdapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
//
//        ListView listView = findViewById(R.id.listview);
//        listView.setAdapter(mAdapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Fruit fruit = fruitList.get(position);
//                Toast.makeText(MainActivity.this, fruit.getName(), Toast.LENGTH_SHORT).show();
//            }
//        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

//        int spanCount = 4;
//        GridLayoutManager layoutManager = new GridLayoutManager(this, spanCount, GridLayoutManager.VERTICAL, false);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitRCAdapter adapter = new FruitRCAdapter(fruitList);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 避免activity被回收后, 数据无法保存.
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String tmpData = "Something you just typed.";
        Log.d(TAG, "onSaveInstanceState: " + tmpData);
        outState.putString("data_key", tmpData);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void nativeInit();

    public native void nativeFree();

    /**
     * 活动全局点击事件监听
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");

        switch (view.getId()) {
            case R.id.button1:
                ((Button) view).setText(stringFromJNI());
                break;
            case R.id.button_goto_imactivity:
                Log.d(TAG, "click button and goto imactivity");
                Intent intent = new Intent(MainActivity.this, FragmentDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_toast_edittext:
                String inputText = mEditText.getText().toString();
                if (!inputText.equals("")) {
                    Toast.makeText(MainActivity.this, inputText, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_change_imageview:
                Drawable ic_launcher = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher, null);
                Drawable ic_launcher_round = ResourcesCompat.getDrawable(getResources(), R.mipmap.ic_launcher_round, null);
                if (mImageView.getDrawable().getConstantState().equals(ic_launcher.getConstantState())) {
                    mImageView.setImageResource(R.mipmap.ic_launcher_round);
                } else if (mImageView.getDrawable().getConstantState().equals(ic_launcher_round.getConstantState())) {
                    mImageView.setImageResource(R.mipmap.ic_launcher);
                } else {
                    Log.d(TAG, "unknown imageview id");
                }
                break;
            case R.id.btn_toggle_alertdialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("A alert dialog")
                        .setMessage("Something important to show")
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface anInterface, int i) {

                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface anInterface, int i) {

                            }
                        })
                        .show();
                break;
            case R.id.force_offline:
                intent = new Intent(getString(R.string.FORCE_OFFLINE_BROADCAST));
                sendBroadcast(intent);
                break;
            default:
                Logger.d(LogUtil.LOG_UNKNOWN_VIEW_IS_CLICKED);
        }
    }

    /**
     * ==================
     * Inline methods
     * ==================
     */
    private void initFruits() {
        /**
         "Apple","Banana","Orange","Watermelon","Pear",
         "Grape","Pineapple","Strawberry","Cherry","Mango"
         **/
        for (int i = 0; i < 2; i++) {
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
}
