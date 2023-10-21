package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Medicine_MyViewHolder extends RecyclerView.ViewHolder{


    TextView medicineText;
    TextView companyText;
    TextView priceText;

    public Medicine_MyViewHolder(@NonNull View itemView) {
        super(itemView);

        medicineText=(TextView) itemView.findViewById(R.id.medicine_name);
        companyText=(TextView) itemView.findViewById(R.id.company_name);
        priceText=(TextView) itemView.findViewById(R.id.medicine_price);

    }
}
