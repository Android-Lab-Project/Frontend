package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
        holder.AppointmentDate.setText("" +myItemPosition.getApppointment_date());
        holder.DoctorContact.setText("" + myItemPosition.getDoctor_contact());
       // holder.appointmentId.setText(String.valueOf(myItemPosition.getAppointmentId()));
        holder.appointmentId.setText("Serial Number : " + myItemPosition.getAppointmentId());

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,myItemPosition.getAppointmentId().toString(),Toast.LENGTH_SHORT).show();
            //   new CancelAppointment().cancelFunc(myItemPosition.getAppointmentId());
               StaticVariable.cancelIdStatic= myItemPosition.getAppointmentId();
               loaddata();
            }
        });


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


    public void loaddata(){

        String url = StaticVariable.araf + "/delete/appointment?id="+StaticVariable.cancelIdStatic.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Your Appointment is cancel!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, DoctorMyMain.class);
                context.startActivity(intent);
            }



        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Hello Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }


}
