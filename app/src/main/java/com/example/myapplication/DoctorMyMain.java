package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
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
import java.util.List;

public class DoctorMyMain extends AppCompatActivity {

    RecyclerView recyclerView;

   // String url = StaticVariable.araf+"/doctor/appointment?email="+StaticVariable.email;

    String url = StaticVariable.abrar+"/doctor/appointment?email="+"c@gmail.com";

    DoctorMyList_MyAdapter myAdapter;
    List<DoctorMyList_item> listItems;
    Context context;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mydoctor_main);

        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView23);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listItems = new ArrayList<>();
        loadData();


    }

    public void loadData() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

        Toast.makeText(context, "Server is loading!", Toast.LENGTH_SHORT).show();



        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server is Okay!", Toast.LENGTH_SHORT).show();

                String header,date,img;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //  JSONArray array = jsonObject.getJSONArray("MyData");
                    JSONArray array = jsonObject.getJSONArray("appointments");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);

                        header=  receive.getString("doctorName");
                        //   header = receive.getString("headerText");
                        date=  receive.getString("date");
                        //     desc = receive.getString("descText");
                        img=  receive.getString("doctorPic");
                        // img = receive.getString("imgLocation");


                        DoctorMyList_item item = new DoctorMyList_item(
                                header,img,date
                        );
                        listItems.add(item);

                    }
                    myAdapter = new DoctorMyList_MyAdapter(listItems, context);
                    recyclerView.setAdapter(myAdapter);

                    Toast.makeText(context, "Server is Okay and Okay!", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Server Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

}