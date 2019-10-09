package com.gudigudigudi.mdtemplate.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.model.Fruit;

/**
 * Created by gu on 10/18/17.
 */

public class GridFruitAdapter extends ArrayAdapter<Fruit> {

    private int resourceId;
    private boolean isShowDelete = false;

    public GridFruitAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get current item
        final Fruit fruit = getItem(position);
        View view;
        GridFruitAdapter.ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new GridFruitAdapter.ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            viewHolder.deleteImage = view.findViewById(R.id.iv_delete_fruit);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (GridFruitAdapter.ViewHolder) view.getTag();
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        viewHolder.deleteImage.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);

        if (isShowDelete) {
            viewHolder.deleteImage.setOnClickListener(view1 -> remove(fruit));
        }

        return view;
    }

    /**
     * Fruit控件实例的缓存
     */
    class ViewHolder {
        ImageView fruitImage, deleteImage;
        TextView fruitName;
    }
}
