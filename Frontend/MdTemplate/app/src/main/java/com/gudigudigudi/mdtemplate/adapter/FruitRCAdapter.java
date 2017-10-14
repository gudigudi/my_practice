package com.gudigudigudi.mdtemplate.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.activity.FruitContentActivity;
import com.gudigudigudi.mdtemplate.model.Fruit;

import java.util.List;

/**
 * Created by gu on 9/26/17.
 */

public class FruitRCAdapter extends RecyclerView.Adapter<FruitRCAdapter.ViewHolder> {

    private List<Fruit> fruitList;
    private Context context;

    public FruitRCAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup group, final int i) {

        if (context == null) {
            context = group.getContext();
        }

        View view = LayoutInflater.from(context)
                .inflate(R.layout.fruit_item, group, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = viewHolder.getAdapterPosition();
                Fruit fruit = fruitList.get(position);
                Intent intent = new Intent(context, FruitContentActivity.class);
                intent.putExtra(FruitContentActivity.FRUIT_NAME, fruit.getName());
                intent.putExtra(FruitContentActivity.FRUIT_IMAGE_ID, fruit.getImageId());
                context.startActivity(intent);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        Fruit fruit = fruitList.get(i);
//        holder.fruitImage.setImageResource(fruit.getImageId());
        Glide.with(context).load(fruit.getImageId()).into(holder.fruitImage);
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.fruit_image);
            fruitName = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
