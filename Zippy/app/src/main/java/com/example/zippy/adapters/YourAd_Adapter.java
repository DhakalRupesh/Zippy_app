package com.example.zippy.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class YourAd_Adapter extends RecyclerView.Adapter<YourAd_Adapter.YourAdViewHolder> {
    @NonNull
    @Override
    public YourAdViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull YourAdViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class YourAdViewHolder extends RecyclerView.ViewHolder {
        public YourAdViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
