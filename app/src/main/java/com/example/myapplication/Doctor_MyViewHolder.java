package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Doctor_MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;



    TextView headText;
    TextView descText;
    TextView hospital;


    Button bookBtn;

    public Doctor_MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.doctor_item_image);
        headText = (TextView) itemView.findViewById(R.id.doctor_view_title);
        descText = (TextView) itemView.findViewById(R.id.text_view_description);
        bookBtn = (Button) itemView.findViewById(R.id.appointmentBtn);
        hospital = (TextView) itemView.findViewById(R.id.hospital);

    }
}
