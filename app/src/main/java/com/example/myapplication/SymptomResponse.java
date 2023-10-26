package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SymptomResponse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_response);
        TextView diseaseView = findViewById(R.id.disease_view);
        String response = getIntent().getStringExtra("response");
        diseaseView.setText("You Probably Have : " + response);

    }
}