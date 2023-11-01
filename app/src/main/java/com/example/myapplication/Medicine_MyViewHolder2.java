package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Medicine_MyViewHolder2 extends RecyclerView.ViewHolder {

    TextView medicineText;
    TextView companyText;
    TextView priceText;

    public Medicine_MyViewHolder2(@NonNull View itemView) {
        super(itemView);

        medicineText=(TextView) itemView.findViewById(R.id.medicine_name2);
        companyText=(TextView) itemView.findViewById(R.id.company_name2);
        priceText=(TextView) itemView.findViewById(R.id.medicine_price2);

    }
}
