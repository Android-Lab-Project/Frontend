package com.example.myapplication;

import android.media.Image;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NutritionViewHolder extends RecyclerView.ViewHolder {
    public NutritionViewHolder(@NonNull View itemView) {
        super(itemView);
        ImageView foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
        TextView foodQuantity = (TextView) itemView.findViewById(R.id.foodQuantity);
        TextView calories = (TextView) itemView.findViewById(R.id.calories);
        TextView fat = (TextView) itemView.findViewById(R.id.fat);
    }
}
