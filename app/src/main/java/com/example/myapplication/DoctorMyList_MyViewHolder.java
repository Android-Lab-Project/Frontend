package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorMyList_MyViewHolder   extends RecyclerView.ViewHolder{






    TextView DoctorNameText;
    TextView AppointmentDate;

    TextView DoctorContact;

    ImageView imageViewDoctor;

    Button cancel;

    TextView appointmentId;


    public DoctorMyList_MyViewHolder (@NonNull View itemView) {
        super(itemView);
        DoctorNameText=(TextView) itemView.findViewById(R.id.mydoctor_view_title);
        AppointmentDate=(TextView) itemView.findViewById(R.id.myappointmenttime);
     //  imageViewDoctor=(ImageView) itemView.findViewById(R.id.mydoctor_item_image);
        DoctorContact=(TextView) itemView.findViewById(R.id.mydoctor_view_con);
        cancel=(Button) itemView.findViewById(R.id.Cancelfor);
        appointmentId=(TextView) itemView.findViewById(R.id.mydoctor_view_idHello);
    }


}
