package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
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

public class DoctorProfile extends AppCompatActivity {

    private TextView EditDoctorSignUpName, EditDoctorSignUpEmail, EditDoctorSignUpContactNumber;

    private TextView EditDoctorDegree, EditDoctorHospitalName;

    Context context;
    ProgressDialog progressDialog;
    String url = StaticVariable.araf + "/doctor/one?email=" + StaticVariable.email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        context=this;

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

            try {
                // Parse the JSON response
                JSONObject jsonObject = new JSONObject(response);

                // Assuming the JSON structure includes the following fields
                String name = jsonObject.getString("firstName");
                String email = jsonObject.getString("email");
                String phone_number = jsonObject.getString("contactNo");
                String doctor_deg = jsonObject.getString("degrees");
                String doctor_hospital = jsonObject.getString("currentHospital");

                EditDoctorSignUpName = findViewById(R.id.doctor_name);
                EditDoctorSignUpName.setText(name);

                EditDoctorSignUpEmail = findViewById(R.id.doctor_email);
                EditDoctorSignUpEmail.setText(email);

                EditDoctorSignUpContactNumber = findViewById(R.id.doctor_phone);
                EditDoctorSignUpContactNumber.setText(phone_number);

                EditDoctorDegree = findViewById(R.id.doctor_specialite);
                EditDoctorDegree.setText(doctor_deg);

                EditDoctorHospitalName = findViewById(R.id.doctor_address);
                EditDoctorHospitalName.setText(doctor_hospital);

//                TextView nameTextView = findViewById(R.id.nameTextView);
//                nameTextView.setText(name);


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, "Error parsing response", Toast.LENGTH_SHORT).show();
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