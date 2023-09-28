
package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends Fragment {
    RecyclerView recyclerView;
    String url = "https://api.jsonserve.com/YZ2tHr";

   // String url = "https://api.jsonserve.com/tUrrp9";
    Doctor_MyAdapter myAdapter;
    List<Doctor_MyItem> listItems;
    Context context;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor, container, false);
        context = getContext();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        listItems = new ArrayList<>();
        loadData();

        return view;
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
                String header,desc,img;
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("MyData");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject receive = array.getJSONObject(i);

                        header=  receive.getString("headerText");
                        desc=  receive.getString("descText");
                        img=  receive.getString("imgLocation");

                        Doctor_MyItem item = new Doctor_MyItem(
                               header,desc,img
                        );
                        listItems.add(item);
                     //   Log.e("TAG",header+" "+desc+" "+img);
                    }
                    myAdapter = new Doctor_MyAdapter(listItems, context);
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