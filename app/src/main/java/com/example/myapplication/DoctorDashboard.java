package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DoctorDashboard extends AppCompatActivity {



    private Button bt2 ;
    private Button buttonMedicine;
    private Button buttonAI;

    private Button buttonProfile;

    private Button buttonSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        bt2= (Button) findViewById (R.id.appointmentDetailForDoctor);
       buttonMedicine= (Button) findViewById (R.id.myDoctorMedicine23);
        buttonAI= (Button) findViewById (R.id.aiFeaturesDoctor);
        buttonProfile=(Button)findViewById(R.id.myDoctorProfile);
        buttonSignout=(Button)findViewById(R.id.signOutDoctor);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openBook();

            }
        });

        buttonSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openSignOut();

            }
        });

        buttonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openProfile();

            }
        });

        buttonMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openMedicine();

            }
        });

        buttonAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openAI();

            }
        });
    }

    public void openBook() {
        Intent intent = new Intent(this, PatientMain.class);
        startActivity(intent);
    }

    public void openSignOut() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        }, 2000);
    }


    public void openAI() {
        Intent intent = new Intent(this, AIConsultation.class);
        startActivity(intent);
    }
    public void openMedicine() {
        try {

            Intent intent = new Intent(this, MedicineActivity2.class);
            startActivity(intent);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this,"Hello World",Toast.LENGTH_LONG).show();
            // Log the exception for debugging purposes
        }
    }

    public  void openProfile(){
        Intent intent = new Intent(this, DoctorProfile.class);
        startActivity(intent);
    }


}