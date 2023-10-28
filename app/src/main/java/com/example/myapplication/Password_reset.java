package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Password_reset extends AppCompatActivity {

    Button btnBack;

    Button sendDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        btnBack = (Button) findViewById(R.id.btnBack);
        sendDemo=(Button) findViewById(R.id.sendForget);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        sendDemo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                open();

            }
        });




    }

    public void open() {
        Intent intent = new Intent(this,DocterNew.class);
        startActivity(intent);
    }
}
