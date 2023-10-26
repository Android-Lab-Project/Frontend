package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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
import java.util.HashMap;
import java.util.Map;

public class SymptomActivity extends AppCompatActivity {
    RequestQueue mRequestQueue;
    StringRequest mStringRequest;
    ArrayList<Integer> symptoms = new ArrayList<>();
    String symptomString = new String();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom);
        context = this;
        HashMap<String, Integer> symptomDict = new HashMap<>();
        symptomDict.put("fatigue", 28);
        symptomDict.put("chills", 6);
        symptomDict.put("cough", 12);
        symptomDict.put("muscle_weakness", 106);
        symptomDict.put("swelling_joints", 113);
        symptomDict.put("acidity", 9);
        symptomDict.put("vomiting", 11);
        symptomDict.put("itching", 0);
        symptomDict.put("high_fever", 26);
        symptomDict.put("headache", 44);
        symptomDict.put("nausea", 15);
        symptomDict.put("muscle_pain", 59);
        symptomDict.put("chest_pain", 13);
        symptomDict.put("sweating", 58);
        symptomDict.put("stomach_pain", 8);
        symptomDict.put("skin_rash", 1);
        symptomDict.put("stiff_neck", 48);
        symptomDict.put("abdominal_pain", 17);

        CheckBox fatigue = findViewById(R.id.fatigue);
        CheckBox chills = findViewById(R.id.chills);
        CheckBox cough = findViewById(R.id.cough);
        CheckBox muscle_weakness = findViewById(R.id.muscle_weakness);
        CheckBox swelling_joints = findViewById(R.id.swelling_joints);
        CheckBox acidity = findViewById(R.id.acidity);
        CheckBox vomiting = findViewById(R.id.vomiting);
        CheckBox itching = findViewById(R.id.itching);
        CheckBox high_fever = findViewById(R.id.high_fever);
        CheckBox headache = findViewById(R.id.headache);
        CheckBox nausea = findViewById(R.id.nausea);
        CheckBox muscle_pain = findViewById(R.id.muscle_pain);
        CheckBox chest_pain = findViewById(R.id.chest_pain);
        CheckBox sweating = findViewById(R.id.sweating);
        CheckBox stomach_pain = findViewById(R.id.stomach_pain);
        CheckBox skin_rash = findViewById(R.id.skin_rash);
        CheckBox stiff_neck = findViewById(R.id.stiff_neck);
        CheckBox abdominal_pain = findViewById(R.id.abdominal_pain);

        Button submit_button = (Button) findViewById(R.id.submit_button);

        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fatigue.isChecked()) symptoms.add(symptomDict.get("fatigue"));
                if(chills.isChecked()) symptoms.add(symptomDict.get("chills"));
                if(cough.isChecked()) symptoms.add(symptomDict.get("cough"));
                if(muscle_weakness.isChecked()) symptoms.add(symptomDict.get("muscle_weakness"));
                if(swelling_joints.isChecked()) symptoms.add(symptomDict.get("swelling_joints"));
                if(vomiting.isChecked()) symptoms.add(symptomDict.get("vomiting"));
                if(acidity.isChecked()) symptoms.add(symptomDict.get("acidity"));
                if(itching.isChecked()) symptoms.add(symptomDict.get("itching"));
                if(high_fever.isChecked()) symptoms.add(symptomDict.get("high_fever"));
                if(headache.isChecked()) symptoms.add(symptomDict.get("headache"));
                if(nausea.isChecked()) symptoms.add(symptomDict.get("nausea"));
                if(muscle_pain.isChecked()) symptoms.add(symptomDict.get("muscle_pain"));
                if(chest_pain.isChecked()) symptoms.add(symptomDict.get("chest_pain"));
                if(sweating.isChecked()) symptoms.add(symptomDict.get("sweating"));
                if(stomach_pain.isChecked()) symptoms.add(symptomDict.get("stomach_pain"));
                if(skin_rash.isChecked()) symptoms.add(symptomDict.get("skin_rash"));
                if(stiff_neck.isChecked()) symptoms.add(symptomDict.get("stiff_neck"));
                if(abdominal_pain.isChecked()) symptoms.add(symptomDict.get("abdominal_pain"));
                StringBuilder result = new StringBuilder();

                for (int i = 0; i < symptoms.size(); i++) {
                    result.append(symptoms.get(i));
                    if (i < symptoms.size() - 1) {
                        result.append(", ");
                    }
                }
                String response = loadData(result.toString());
                Log.d("Response : ", response );
                result.setLength(0);
                symptoms.clear();
                Intent intent = new Intent(SymptomActivity.this, SymptomResponse.class);
                intent.putExtra("response", response);
                startActivity(intent);

            }
        });



    }

    String loadData(String result) {
        mRequestQueue = Volley.newRequestQueue(this);
        String url = "http://10.0.2.2:8000/disease?arr=" + result;
        String response2 = "";
        mStringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Intent intent = new Intent(SymptomActivity.this, SymptomResponse.class);
                    intent.putExtra("response", jsonObject.getString("message"));
                    startActivity(intent);

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
        mRequestQueue.add(mStringRequest);
        return response2;
    }
}