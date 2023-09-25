package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DoctorFragment extends Fragment {

    RecyclerView recyclerView;
    String url="https://api.jsonserve.com/YZ2tHr";
    Doctor_MyAdapter myAdapter;
    List<Doctor_MyItem> listItems;

    Context context;

    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_doctor, container, false);

        context=getContext();
        recyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        //-----------getcontext() instead of this-------
      //  recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       // recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listItems=new ArrayList<>();

        loadData();






       // TextView doctor =(TextView) view.findViewById(R.id.doctor);
//        doctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri.Builder builder = CalendarContract.CONTENT_URI.buildUpon();
//                builder.appendPath("time");
//                ContentUris.appendId(builder, Calendar.getInstance().getTimeInMillis());
//                Intent intent = new Intent(Intent.ACTION_VIEW)
//                        .setData(builder.build());
//                startActivity(intent);
//            }
//        });
        return view;
    }

    public void loadData(){

        //progressDialog=new ProgressDialog(getContext());
      //  progressDialog=new ProgressDialog(this);
        progressDialog=new ProgressDialog(context);

       //progressDialog=new ProgressDialog(getActivity().getApplicationContext());

        progressDialog.setMessage("Loading......");
        progressDialog.show();


        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();

                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONArray array=jsonObject.getJSONArray("MyData");

                            for(int i=0;i<array.length();i++){

                                JSONObject receive=array.getJSONObject(i);

                                Doctor_MyItem item=new Doctor_MyItem(
                                        receive.getString("headerText"),
                                        receive.getString("descText"),
                                        receive.getString("imgLocation")
                                );

                                listItems.add(item);

                            }

                          //  myAdapter=new Doctor_MyAdapter(listItems,getContext());
                            //myAdapter=new Doctor_MyAdapter(listItems,getApplicationContext());

                         //   myAdapter=new Doctor_MyAdapter(listItems,getActivity().getApplicationContext());

                            myAdapter=new Doctor_MyAdapter(listItems,context);
                            recyclerView.setAdapter(myAdapter);

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),"Server Error !",Toast.LENGTH_SHORT).show();
                 //Toast.makeText(DoctorFragment.this,"Server Error !",Toast.LENGTH_SHORT).show();
            }
        });

       // RequestQueue queue = Volley.newRequestQueue(this);
        //RequestQueue queue = Volley.newRequestQueue(getContext());

      //  RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());

        RequestQueue queue = Volley.newRequestQueue(context);


        queue.add(stringRequest);



    }


}