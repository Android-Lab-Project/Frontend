package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class chooseOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_option);

        Button userButton = (Button) findViewById(R.id.userRole);

        Button doctorButton = (Button) findViewById(R.id.doctorRole);

        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openUserPage();
            }
        });

        doctorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDoctorPage();
            }
        });
    }

    public void openUserPage() {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void openDoctorPage() {
        Intent intent = new Intent(this, DoctorSignUp.class);
        startActivity(intent);
    }
}