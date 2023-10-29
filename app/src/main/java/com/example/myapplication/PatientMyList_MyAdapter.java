package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PatientMyList_MyAdapter extends RecyclerView.Adapter<PatientMyList_MyViewHolder>{

    private List<PatientMyList_item> Mylist;
    private Context context;





    public PatientMyList_MyAdapter(List<PatientMyList_item> mylist,Context context) {
        Mylist = mylist;
        this.context=context;
    }

    @NonNull
    @Override
    public PatientMyList_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mypatientlistlayout,parent,false);
        PatientMyList_MyViewHolder VH=new PatientMyList_MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull   PatientMyList_MyViewHolder holder, int position) {

        final  PatientMyList_item myItemPosition= Mylist.get(position);
        holder.PatientNameText.setText(myItemPosition.getPatientName());
        holder.PatientProblemText.setText(myItemPosition.getPatientProblem());
        holder.AppointmentDateText.setText(myItemPosition.getApppointmentDate_for());
        holder.Patientcontact.setText(myItemPosition.getPatientContactInformation());

        //Picasso.get().load(myItemPosition.getDoctor_pic()).into(holder.imageViewDoctor);

        holder.PatientNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getPatientProblem(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {

        return Mylist.size();
    }
}
