package com.gudigudigudi.mdtemplate.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gudigudigudi.mdtemplate.R;
import com.gudigudigudi.mdtemplate.activity.FruitContentActivity;
import com.gudigudigudi.mdtemplate.model.Fruit;

import java.util.List;


/**
 * Created by gu on 9/26/17.
 */

public class FruitRCAdapter extends RecyclerView.Adapter<FruitRCAdapter.InnerViewHolder> {

    private List<Fruit> fruitList;
    private Context context;

    public FruitRCAdapter(List<Fruit> fruitList) {
        this.fruitList = fruitList;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup group, final int i) {

        if (context == null) {
            context = group.getContext();
        }

        View view = LayoutInflater.from(context)
                .inflate(R.layout.fruit_item, group, false);
        final InnerViewHolder viewHolder = new InnerViewHolder(view);

        viewHolder.fruitImage.setOnClickListener(view1 -> {
            int position = viewHolder.getAdapterPosition();
            Fruit fruit = fruitList.get(position);
            Intent intent = new Intent(context, FruitContentActivity.class);
            intent.putExtra(FruitContentActivity.FRUIT_NAME, fruit.getName());
            intent.putExtra(FruitContentActivity.FRUIT_IMAGE_ID, fruit.getImageId());
            context.startActivity(intent);
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InnerViewHolder holder, int i) {
        Fruit fruit = fruitList.get(i);
        Glide.with(context).load(fruit.getImageId()).into(holder.fruitImage);
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }

    static class InnerViewHolder extends RecyclerView.ViewHolder {
        ImageView fruitImage;
        TextView fruitName;
        CardView cardView;

        public InnerViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView;
            fruitImage = itemView.findViewById(R.id.fruit_image);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }
}
