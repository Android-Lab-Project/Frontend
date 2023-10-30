package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PatientMyList_MyViewHolder  extends RecyclerView.ViewHolder{

    TextView PatientNameText;
    TextView PatientProblemText;
    TextView AppointmentDateText;
    TextView Patientcontact;

    TextView patientAppointmentTd;
    Button cancel2;




    public  PatientMyList_MyViewHolder (@NonNull View itemView) {
        super(itemView);
       PatientNameText=(TextView) itemView.findViewById(R.id.mypatient_view_name);
        PatientProblemText=(TextView) itemView.findViewById(R.id.mypatient_view_problem);
        AppointmentDateText=(TextView) itemView.findViewById(R.id.myappointmenttimefor2);
        Patientcontact=(TextView)itemView.findViewById(R.id.mypatient_view_contactFor);
        patientAppointmentTd=(TextView) itemView.findViewById(R.id.mypatient_view_id);
        cancel2=(Button) itemView.findViewById(R.id.myappointmentCancelPatient);
    }
}
