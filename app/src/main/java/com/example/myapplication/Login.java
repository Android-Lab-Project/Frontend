package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.TestLooperManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText loginInEmail,loginInPassword;
    private  TextView signUpTextView;
    private Button loginInButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginInEmail=(EditText) findViewById(R.id.loginInEmail);
        loginInPassword=findViewById(R.id.loginInPassword);
        loginInButton= findViewById(R.id.loginInButton);
        signUpTextView=findViewById(R.id.signUpTextViewId);

        signUpTextView.setOnClickListener(this);
        loginInButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.loginInButton){

        }
        else if(view.getId()==R.id.signUpTextViewId){
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }



    }



//    public void signInClicked(View view) {
//        Intent intent = new Intent(this, DashBoard.class);
//        startActivity(intent);
//    }
//
//    public void signUpClicked(View view) {
//        Intent intent = new Intent(this, Signup.class);
//        startActivity(intent);
//    }

    public void forgotButtonClicked(View view) {
        Intent intent = new Intent(this, Password_reset.class);
        startActivity(intent);
    }


}