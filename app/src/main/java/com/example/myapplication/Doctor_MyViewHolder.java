package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Doctor_MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView headText;
    TextView descText;

    public Doctor_MyViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView=(ImageView) itemView.findViewById(R.id.imageView);
        headText=(TextView) itemView.findViewById(R.id.headId);
        descText=(TextView) itemView.findViewById(R.id.descId);


    }
}
