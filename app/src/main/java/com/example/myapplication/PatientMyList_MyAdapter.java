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
        holder.patientAppointmentTd.setText("" + myItemPosition.getId());

        //Picasso.get().load(myItemPosition.getDoctor_pic()).into(holder.imageViewDoctor);

        holder.cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,myItemPosition.getId().toString(),Toast.LENGTH_SHORT).show();
                //   new CancelAppointment().cancelFunc(myItemPosition.getAppointmentId());
                StaticVariable.cancelIdStatic= myItemPosition.getId();//same static variable
                Loaddata();
            }
        });

        holder.PatientNameText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context,myItemPosition.getPatientProblem(),Toast.LENGTH_SHORT).show();
            }
        });



    }


    public void Loaddata(){

        String url = StaticVariable.araf + "/delete/appointment?id="+StaticVariable.cancelIdStatic.toString();

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(context, "Your Appointment is cancel!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context, PatientMain.class);
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


    @Override
    public int getItemCount() {

        return Mylist.size();
    }
}
