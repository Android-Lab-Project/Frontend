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
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MedicineActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

    //String url ="https://api.jsonserve.com/9ehGWC";
    // String url ="https://api.jsonserve.com/j5Uocl";

    //  String url = StaticVariable.araf+"/medicine/all";
    String url="https://api.jsonserve.com/mhSeNz";
    Medicine_MyAdapter2 myAdapter;
    List<Medicine_MyItem2> listItems;
    Context context;
    ProgressDialog progressDialog;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine2);

        context = this;

        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMedicine2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchView = findViewById(R.id.searchViewMedicine2);
        searchView.clearFocus();


        listItems = new ArrayList<>();
        loadData();
        searchMedicine();


    }


    public void searchMedicine(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Handle the search query submission if needed
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Filter the data based on the search query
                filterlist333(newText);
                return true;
            }
        });

    }

    private void filterlist333(String text){
        List<Medicine_MyItem2>filerList=new ArrayList<>();

        for(Medicine_MyItem2 item:listItems){
            if(item.getMedicine_name().toLowerCase().contains(text.toLowerCase())){
                filerList.add(item);
            }

        }

        if(filerList.isEmpty()){
            Toast.makeText(context, "No data is found!", Toast.LENGTH_SHORT).show();
        }else{
            myAdapter.setFilterList3(filerList);
        }

    }

    public void loadData() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading......");
        progressDialog.show();

       // Toast.makeText(context, "Server is loading!", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
             //   Toast.makeText(context, "Server is Okay!", Toast.LENGTH_SHORT).show();
                String medicine_name,company_name,medicine_price;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("medicines");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);

                        medicine_name=  receive.getString("name");
                        company_name=  receive.getString("company");
                        medicine_price=  receive.getString("price");

                        Medicine_MyItem2 item = new Medicine_MyItem2(
                                medicine_name,company_name,medicine_price
                        );
                        listItems.add(item);
                        //   Log.e("TAG",header+" "+desc+" "+img);
                    }
                    myAdapter = new Medicine_MyAdapter2(listItems, context);
                    recyclerView.setAdapter(myAdapter);

                  //  Toast.makeText(context, "Server is Okay and Okay!", Toast.LENGTH_SHORT).show();
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

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });

        queue.add(stringRequest);
    }
}