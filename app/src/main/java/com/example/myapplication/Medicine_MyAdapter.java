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

public class Medicine_MyAdapter extends RecyclerView.Adapter<Medicine_MyViewHolder> {

    private List<Medicine_MyItem> Mylist;
    private Context context;



    public Medicine_MyAdapter(List<Medicine_MyItem> mylist,Context context) {
        Mylist = mylist;
        this.context=context;
    }

    public void setFilterList3(List<Medicine_MyItem>filterList){
        this.Mylist=filterList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Medicine_MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.medicine_layout,parent,false);
        Medicine_MyViewHolder VH=new Medicine_MyViewHolder(v);
        return VH;
    }

    @Override
    public void onBindViewHolder(@NonNull Medicine_MyViewHolder holder, int position) {

        final  Medicine_MyItem myItemPosition= Mylist.get(position);
        holder.medicineText.setText(myItemPosition.getMedicine_name());
        holder.companyText.setText(myItemPosition.getCompany_name());
        holder.priceText.setText(myItemPosition.getMedicine_price());

        //Picasso.get().load(myItemPosition.getImageUrl()).into(holder.imageView);

        holder.medicineText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getMedicine_name(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {

        return Mylist.size();
    }
}
