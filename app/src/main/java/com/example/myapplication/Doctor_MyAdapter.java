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

public class Doctor_MyAdapter extends RecyclerView.Adapter<Doctor_MyViewHolder> {

    private List<Doctor_MyItem> Mylist;
    private Context context;

    public static final String key1="msg";
    public static final String key2="desc";
    public static final String key3="photo";

    public Doctor_MyAdapter(List<Doctor_MyItem> mylist,Context context) {
        Mylist = mylist;
        this.context=context;
    }

    @NonNull
    @Override
    public Doctor_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_rowlayout,parent,false);
        Doctor_MyViewHolder VH=new Doctor_MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull Doctor_MyViewHolder holder, int position) {

        final  Doctor_MyItem myItemPosition= Mylist.get(position);
        holder.headText.setText(myItemPosition.getHead());
        holder.descText.setText(myItemPosition.getDesc());
        Picasso.get().load(myItemPosition.getImageUrl()).into(holder.imageView);

        holder.headText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getHead(),Toast.LENGTH_SHORT).show();
            }
        });


        //bookbtn----------------
        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String doctor_name = myItemPosition.getHead();
                String doctor_des = myItemPosition.getDesc();
                String doctor_photo = myItemPosition.getImageUrl(); // Change data type to String

                Intent intent = new Intent(view.getContext(), Doctor_Appointment1.class);
                intent.putExtra(key1, doctor_name);
                intent.putExtra(key2, doctor_des);
                intent.putExtra(key3, doctor_photo);

                view.getContext().startActivity(intent);
            }
        });



//        //bookbtn----------------
//        holder.bookBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String doctor_name= myItemPosition.getHead();
//                String doctor_des=myItemPosition.getDesc();
//                int doctor_photo=Integer.parseInt(myItemPosition.getImageUrl());
//
//                Intent intent = new Intent(view.getContext(), Doctor_Appointment1.class);
//                intent.putExtra(key1,doctor_name);
//                intent.putExtra(key2,doctor_des);
//                intent.putExtra(key3,doctor_photo);
//
//
//                view.getContext().startActivity(intent);
//            }
//        });


    }

    @Override
    public int getItemCount() {

        return Mylist.size();
    }
}
