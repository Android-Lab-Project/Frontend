package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Doctor_Appointment1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment1);
        Button bt = (Button) findViewById (R.id.getAppointment);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppoointment();
            }
        });


    }
    public void openAppoointment() {
        Intent intent = new Intent(this, Docter_ConfirmAppointment.class);
        startActivity(intent);
    }


}