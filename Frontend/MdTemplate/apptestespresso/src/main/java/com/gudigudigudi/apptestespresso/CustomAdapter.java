package com.gudigudigudi.apptestespresso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.VH> {

    private final List<String> mDataSet;
    private final Context mContext;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_text_row, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (position == mDataSet.size() / 2) {
            holder.setInTheMiddle(true);
            holder.getTextView().setText(mContext.getResources().getString(R.string.middle));
        } else {
            holder.setInTheMiddle(false);
            holder.getTextView().setText(mDataSet.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        @Getter
        @Setter
        private boolean isInTheMiddle = false;
        @Getter
        private final TextView textView;

        public VH(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
