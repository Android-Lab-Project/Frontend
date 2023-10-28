package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.ktx.Firebase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity implements View.OnClickListener {




    private EditText signUpName,signUpEmail,signUpPassword,signUpContactNumber;
    private Button signUpButton;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressbarId);

        signUpName=findViewById(R.id.name);
        signUpEmail=findViewById(R.id.email);
        signUpPassword=findViewById(R.id.password);
        signUpContactNumber=findViewById(R.id.phone_number);
        signUpButton=findViewById(R.id.signup);

        signUpButton.setOnClickListener(this);





    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.signup){
            userRegister();

        }else if(view.getId()==R.id.signInTextId){
            Intent intent=new Intent(getApplicationContext(),Login.class);
            startActivity(intent);
        }

    }

    public  void userRegister(){

        String  name=signUpName.getText().toString().trim();
       //String  email=signUpEmail.getText().toString().trim();
        String email = signUpEmail.getText().toString().trim();
        String  password=signUpPassword.getText().toString().trim();
       String  phone_number=signUpContactNumber.getText().toString().trim();


        if(name.isEmpty()){
            signUpName.setError("Enter your name");
            signUpName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            signUpEmail.setError("Enter an email Address");
            signUpEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            signUpPassword.setError("Enter your password");
            signUpPassword.requestFocus();
            return;
        }
        if(phone_number.isEmpty()){
            signUpContactNumber.setError("Enter your phone Number");
            signUpContactNumber.requestFocus();
            return;
        }



//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            signUpEmail.setError("Enter a valid email address");
//            signUpEmail.requestFocus();
//            return;
//        }

        if(password.length()<6){
            signUpPassword.setError("Minimum length of a password should be 6");
            signUpPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(),"Registration is happening",Toast.LENGTH_SHORT).show();

                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"Registration is Successful",Toast.LENGTH_SHORT).show();
                    //finish();
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                  //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                   // finish();

                }
                else {

                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already Register",Toast.LENGTH_SHORT).show();
                    }
                    else{

                        Toast.makeText(getApplicationContext(),"Error :"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();

                    }

                    //Toast.makeText(getApplicationContext(),"Registration is Not Successful",Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

    public void signInClick(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


//    public void signUpClicked(View view) {
//        Intent intent = new Intent(this, DashBoard.class);
//        startActivity(intent);
//    }
}