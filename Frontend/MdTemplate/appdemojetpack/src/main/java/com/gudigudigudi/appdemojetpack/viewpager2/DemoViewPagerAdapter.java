package com.gudigudigudi.appdemojetpack.viewpager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.gudigudigudi.appdemojetpack.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DemoViewPagerAdapter extends RecyclerView.Adapter<DemoViewPagerAdapter.EventViewHolder> {

    List<String> eventList=new ArrayList<>(Arrays.asList("0","1","2"));

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new EventViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_demo_viewpager2_cell, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        TextView textView=(TextView)holder.itemView;
        textView.setText("Page "+eventList.get(position));

        @ColorRes
        int backgroundColorResId = position % 2 == 0? R.color.blue: R.color.orange;

        textView.setBackgroundColor(ContextCompat.getColor(textView.getContext(), backgroundColorResId));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    class EventViewHolder extends RecyclerView.ViewHolder {

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
