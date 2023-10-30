package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Diagnosis_MyAdapter extends RecyclerView.Adapter<Diagnosis_MyViewHolder> {

    private List<Diagnosis_MyItem> Mylist;
    private Context context;

    public Diagnosis_MyAdapter(List<Diagnosis_MyItem> mylist,Context context) {
        Mylist = mylist;
        this.context=context;
    }

    public void setFilterList(List<Diagnosis_MyItem>filterList){
        this.Mylist=filterList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Diagnosis_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.diagnosis_layout,parent,false);
        Diagnosis_MyViewHolder VH=new Diagnosis_MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull Diagnosis_MyViewHolder holder, int position) {

        final  Diagnosis_MyItem myItemPosition= Mylist.get(position);
        holder.diagnosisNameText.setText(myItemPosition.getDiagnosis_name());
        holder.hospitalNameText.setText(myItemPosition.getHospital_name());
        holder.diagnosisPriceText.setText(myItemPosition.getDiagnosis_price());

        //Picasso.get().load(myItemPosition.getImageUrl()).into(holder.imageView);

        holder.diagnosisNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getDiagnosis_name(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return Mylist.size();
    }
}
