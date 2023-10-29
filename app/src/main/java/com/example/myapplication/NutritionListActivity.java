package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutritionListActivity extends AppCompatActivity {

    List<NutritionItem> foodItemsResponse;
    NutritionAdapter nutritionAdapter;
    RecyclerView recyclerView;
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_list);
        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.nutritionRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String response = getIntent().getStringExtra("response");

        foodItemsResponse = new ArrayList<>();
        loadData(response);
    }


    void loadData(String result) {
        mRequestQueue = Volley.newRequestQueue(this);
        String url = "https://trackapi.nutritionix.com/v2/natural/nutrients";
        String NUTRITIONIX_APPLICATION_ID = "16e0530e";
        String NUTRITIONIX_APPLICATION_KEY = "b6e196da5b71174c0b6fde15beeaa044";
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("query", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String requestBody = jsonBody.toString();
        mStringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray foodItems = jsonObject.getJSONArray("foods");
                            for (int i = 0; i < foodItems.length(); i++) {
                                JSONObject food = foodItems.getJSONObject(i);
                                JSONObject foodImage = (JSONObject) food.get("photo");

                                NutritionItem item = new NutritionItem(
                                        foodImage.get("thumb").toString(),
                                        food.get("food_name").toString(),
                                        food.get("serving_qty").toString(),
                                        food.get("serving_unit").toString(),
                                        food.get("serving_weight_grams").toString(),
                                        food.get("nf_calories").toString(),
                                        food.get("nf_total_fat").toString(),
                                        food.get("nf_cholesterol").toString(),
                                        food.get("nf_protein").toString(),
                                        food.get("nf_total_carbohydrate").toString(),
                                        food.get("nf_sugars").toString()
                                );
                                foodItemsResponse.add(item);
                            }
                            nutritionAdapter = new NutritionAdapter(foodItemsResponse, context);
                            recyclerView.setAdapter(nutritionAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("x-app-id",  NUTRITIONIX_APPLICATION_ID);
                params.put("x-app-key", NUTRITIONIX_APPLICATION_KEY);

                return params;
            }
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                    return null;
                }
            }

        };
        mRequestQueue.add(mStringRequest);
    }
}