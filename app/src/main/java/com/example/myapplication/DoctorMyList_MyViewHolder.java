package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorMyList_MyViewHolder   extends RecyclerView.ViewHolder{


    TextView DoctorNameText;
    TextView AppointmentDate;

    ImageView imageViewDoctor;

    public DoctorMyList_MyViewHolder (@NonNull View itemView) {
        super(itemView);
        DoctorNameText=(TextView) itemView.findViewById(R.id.mydoctor_view_title);
        AppointmentDate=(TextView) itemView.findViewById(R.id.myappointmenttime);
        imageViewDoctor=(ImageView) itemView.findViewById(R.id.mydoctor_item_image);
    }
}
