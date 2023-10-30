package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class Diagnosis extends AppCompatActivity {

    RecyclerView recyclerView;

    //String url ="https://api.jsonserve.com/9ehGWC";
    String url ="https://api.jsonserve.com/8iyNum";
    Diagnosis_MyAdapter myAdapter;
    List<Diagnosis_MyItem> listItems;

    Context context;
    ProgressDialog progressDialog;

    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);

        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewDiagnosis);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchView = findViewById(R.id.searchView2);
        searchView.clearFocus();


        listItems = new ArrayList<>();
        loadData();
        searchData();
    }


    public void searchData(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data based on the search query
             filterlist(newText);
                return true;
            }
        });

    }

    private void filterlist(String text){
        List<Diagnosis_MyItem>filerList=new ArrayList<>();

        for(Diagnosis_MyItem item:listItems){
            if(item.getDiagnosis_name().toLowerCase().contains(text.toLowerCase())){
                filerList.add(item);
            }

        }

        if(filerList.isEmpty()){
            Toast.makeText(context, "No data is found!", Toast.LENGTH_SHORT).show();
        }else{
            myAdapter.setFilterList(filerList);
        }

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
                String diagnosis_name, hospital_name, diagnosis_price;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("MyMedicineData");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);
                        diagnosis_name=  receive.getString("diagnosis");
                        hospital_name=  receive.getString("hospital");
                        diagnosis_price=  receive.getString("price");

                        Diagnosis_MyItem item = new Diagnosis_MyItem(
                               diagnosis_name, hospital_name, diagnosis_price
                        );
                        listItems.add(item);
                        //   Log.e("TAG",header+" "+desc+" "+img);
                    }
                    myAdapter = new Diagnosis_MyAdapter(listItems, context);
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

