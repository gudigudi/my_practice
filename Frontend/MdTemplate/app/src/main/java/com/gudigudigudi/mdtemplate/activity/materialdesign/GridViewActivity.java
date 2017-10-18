package com.gudigudigudi.mdtemplate.activity.materialdesign;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.adapter.GridFruitAdapter;
import com.gudigudigudi.mdtemplate.model.Fruit;

import java.util.ArrayList;
import java.util.List;

public class GridViewActivity extends AppCompatActivity {

    private GridView gridView;
    private GridFruitAdapter gridFruitAdapter;
    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = (GridView) findViewById(R.id.gv);

        initFruits();

        gridFruitAdapter = new GridFruitAdapter(GridViewActivity.this, R.layout.fruit_item_grid);

        gridView.setAdapter(gridFruitAdapter);
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> view, View view1, int i, long l) {
//                String name = fruitList.get(i).getName();
//                ImageView imageView = new ImageView(GridViewActivity.this);
//                imageView.setScaleType(ImageView.ScaleType.CENTER);
//                imageView.setLayoutParams(new LinearLayout.LayoutParams(
//                        ViewGroup.LayoutParams.WRAP_CONTENT,
//                        ViewGroup.LayoutParams.WRAP_CONTENT));
//                imageView.setImageResource(fruitList.get(i).getImageId());
//
//                Dialog dialog = new AlertDialog.Builder(GridViewActivity.this)
//                        .setIcon(R.drawable.apple)
//                        .setTitle("The fruit is")
//                        .setView(imageView)
//                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface anInterface, int i) {
//
//                            }
//                        }).create();
//                dialog.show();
//            }
//        });
        gridView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                ImageView imageView = view.findViewById(R.id.iv_delete_fruit);
                imageView.setVisibility(View.VISIBLE);
                Toast.makeText(GridViewActivity.this, "long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
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
