package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DoctorDashboard extends AppCompatActivity {



    private Button bt2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_dashboard);

        bt2= (Button) findViewById (R.id.appointmentDetailForDoctor);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openBook();

            }
        });
    }

    public void openBook() {
        Intent intent = new Intent(this, PatientMain.class);
        startActivity(intent);
    }
}