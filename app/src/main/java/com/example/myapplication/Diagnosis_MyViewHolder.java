package com.example.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Diagnosis_MyViewHolder extends RecyclerView.ViewHolder{

    TextView diagnosisNameText;
    TextView hospitalNameText;
    TextView diagnosisDescriptionText;
    TextView diagnosisPriceText;

    public Diagnosis_MyViewHolder(@NonNull View itemView) {
        super(itemView);

        diagnosisNameText=(TextView) itemView.findViewById(R.id.diagnosis_name);
        hospitalNameText=(TextView) itemView.findViewById(R.id.hospital_name);
        diagnosisDescriptionText=(TextView) itemView.findViewById(R.id.diagnosis_description);
        diagnosisPriceText=(TextView) itemView.findViewById(R.id.diagnosis_price);

    }

}
