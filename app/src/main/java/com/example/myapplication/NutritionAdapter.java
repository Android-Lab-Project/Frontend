package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionViewHolder> {
    private List<NutritionItem> nutritionItemsList;
    private Context context;

    public NutritionAdapter(List<NutritionItem> nutritionItemsList, Context context) {
        this.nutritionItemsList = nutritionItemsList;
        this.context = context;
    }

    @NonNull
    @Override
    public NutritionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.nutrition_card_layout,parent,false);
        NutritionViewHolder viewHolder = new NutritionViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionViewHolder holder, int position) {
        final NutritionItem nutritionItem = nutritionItemsList.get(position);
        ImageView imageUrl = holder.itemView.findViewById(R.id.foodImage);
        Picasso.get().load(nutritionItem.getImageUrl()).into(imageUrl);
        TextView foodName =  holder.itemView.findViewById(R.id.foodName);
        TextView quantity =  holder.itemView.findViewById(R.id.foodQuantity);
        TextView calories =  holder.itemView.findViewById(R.id.calories);
        TextView fat =  holder.itemView.findViewById(R.id.fat);
        TextView cholesterol =  holder.itemView.findViewById(R.id.cholesterol);
        TextView protein =  holder.itemView.findViewById(R.id.protein);
        TextView carbo =  holder.itemView.findViewById(R.id.carbo);
        TextView sugar =  holder.itemView.findViewById(R.id.sugar);
        foodName.setText(nutritionItem.getName());
        quantity.setText("Quantity : " + nutritionItem.getQuantity() + " " + nutritionItem.getUnit() + " " + nutritionItem.getServingWeight() + "g");
        calories.setText("Calories : " + nutritionItem.getCalories() + " Kcal.");
        fat.setText("Fat : " + nutritionItem.getFat() + "g");
        cholesterol.setText("Cholesterol : " + nutritionItem.getCholesterol() + "mg.");
        protein.setText("Protein : " + nutritionItem.getProtein() + "g");
        carbo.setText("Carbohydrate : " + nutritionItem.getCarbohydrate() + "g");
        sugar.setText("Sugar : " + nutritionItem.getSugar() + "g");

    }

    @Override
    public int getItemCount() {
        return nutritionItemsList.size();
    }
}
