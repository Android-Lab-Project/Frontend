package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DocterNew extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docter_new);

        Button button2 = (Button) findViewById (R.id.mydoctor2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAMyList();
            }
        });

    }


    public void openAMyList() {
        Intent intent = new Intent(this, DoctorMyMain.class);
        startActivity(intent);
    }
}