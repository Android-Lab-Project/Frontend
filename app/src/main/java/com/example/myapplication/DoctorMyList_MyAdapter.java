package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DoctorMyList_MyAdapter  extends RecyclerView.Adapter<DoctorMyList_MyViewHolder>{

    private List<DoctorMyList_item> Mylist;
    private Context context;





    public DoctorMyList_MyAdapter(List<DoctorMyList_item> mylist,Context context) {
        Mylist = mylist;
        this.context=context;
    }

    @NonNull
    @Override
    public DoctorMyList_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.mydoctorlistlayout,parent,false);
        DoctorMyList_MyViewHolder VH=new DoctorMyList_MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorMyList_MyViewHolder holder, int position) {

        final  DoctorMyList_item myItemPosition= Mylist.get(position);
        holder.DoctorNameText.setText(myItemPosition.getDoctor_name());
        holder.AppointmentDate.setText(myItemPosition.getApppointment_date());
        holder.DoctorContact.setText(myItemPosition.getDoctor_contact());
        Picasso.get().load(myItemPosition.getDoctor_pic()).into(holder.imageViewDoctor);


        holder.DoctorNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getDoctor_name(),Toast.LENGTH_SHORT).show();
            }
        });



    }

    @Override
    public int getItemCount() {

        return Mylist.size();
    }

}
