package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void signInClicked(View view) {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void signUpClicked(View view) {
        Intent intent = new Intent(this, Signup.class);
        startActivity(intent);
    }

    public void forgotButtonClicked(View view) {
        Intent intent = new Intent(this, Password_reset.class);
        startActivity(intent);
    }
}