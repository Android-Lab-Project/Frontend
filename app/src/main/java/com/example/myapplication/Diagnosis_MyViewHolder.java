package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Diagnosis_MyViewHolder extends RecyclerView.ViewHolder{

    TextView diagnosisNameText;
    TextView hospitalNameText;
    TextView diagnosisPriceText;

    public Diagnosis_MyViewHolder(@NonNull View itemView) {
        super(itemView);
        diagnosisNameText=(TextView) itemView.findViewById(R.id.diagnosis_name);
        hospitalNameText=(TextView) itemView.findViewById(R.id.hospital_name);
        diagnosisPriceText=(TextView) itemView.findViewById(R.id.diagnosis_price);
    }

}
