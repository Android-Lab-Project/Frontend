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

public class PatientMain extends AppCompatActivity {

    RecyclerView recyclerView;

    // String url = StaticVariable.araf+"/doctor/appointment?email="+StaticVariable.email;

    String url = StaticVariable.araf+"/doctor/user/appointment?email="+StaticVariable.email;

    PatientMyList_MyAdapter myAdapter;
    List<PatientMyList_item> listItems;
    Context context;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_main);

        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPatient);
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

                String pName,pProblem,pDate,pContact;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    //  JSONArray array = jsonObject.getJSONArray("MyData");
                    JSONArray array = jsonObject.getJSONArray("appointments");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);

                        pName=  receive.getString("userName");
                        pDate=  receive.getString("date");
                        pProblem=  receive.getString("problem");
                        pContact=receive.getString("userPhone");


                        PatientMyList_item item = new PatientMyList_item(
                              pName,pDate,pProblem,pContact
                        );
                        listItems.add(item);

                    }
                    myAdapter = new PatientMyList_MyAdapter(listItems, context);
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