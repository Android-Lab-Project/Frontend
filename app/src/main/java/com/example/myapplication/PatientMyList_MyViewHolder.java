package com.example.myapplication;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientMyList_MyViewHolder  extends RecyclerView.ViewHolder{

    TextView PatientNameText;
    TextView PatientProblemText;
    TextView AppointmentDateText;
    TextView Patientcontact;



    public  PatientMyList_MyViewHolder (@NonNull View itemView) {
        super(itemView);
       PatientNameText=(TextView) itemView.findViewById(R.id.mypatient_view_name);
        PatientProblemText=(TextView) itemView.findViewById(R.id.mypatient_view_problem);
        AppointmentDateText=(TextView) itemView.findViewById(R.id.myappointmenttimefor2);
        Patientcontact=(TextView)itemView.findViewById(R.id.mypatient_view_contactFor);
    }
}
