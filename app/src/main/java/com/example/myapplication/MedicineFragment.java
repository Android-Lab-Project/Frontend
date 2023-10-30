package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import java.util.List;

public class MedicineFragment extends Fragment {


    RecyclerView recyclerView;

    //String url ="https://api.jsonserve.com/9ehGWC";
 //   String url ="https://api.jsonserve.com/j5Uocl";

    String url = StaticVariable.araf+"/medicine/all";
    Medicine_MyAdapter myAdapter;
    List<Medicine_MyItem> listItems;
    Context context;
    ProgressDialog progressDialog;

    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_medicine, container, false);
        context = getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMedicine);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        searchView = view.findViewById(R.id.searchViewMedicine);
        searchView.clearFocus();


        listItems = new ArrayList<>();
        loadData();
        searchMedicine();

        return view;
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
                filterlist3(newText);
                return true;
            }
        });

    }

    private void filterlist3(String text){
        List<Medicine_MyItem>filerList=new ArrayList<>();

        for(Medicine_MyItem item:listItems){
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

        Toast.makeText(context, "Server is loading!", Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                Toast.makeText(context, "Server is Okay!", Toast.LENGTH_SHORT).show();
                String medicine_name,company_name,medicine_price;

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("medicines");

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);

                        medicine_name=  receive.getString("name");
                        company_name=  receive.getString("company");
                        medicine_price=  receive.getString("price");

                        Medicine_MyItem item = new Medicine_MyItem(
                                medicine_name,company_name,medicine_price
                        );
                        listItems.add(item);
                        //   Log.e("TAG",header+" "+desc+" "+img);
                    }
                    myAdapter = new Medicine_MyAdapter(listItems, context);
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