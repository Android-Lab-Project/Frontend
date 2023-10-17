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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText loginInEmail, loginInPassword;
    private TextView signUpTextView;
    private Button loginInButton;

    ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        loginInEmail = findViewById(R.id.loginInEmail);
        loginInPassword = findViewById(R.id.loginInPassword);
        loginInButton = findViewById(R.id.loginInButton);
        signUpTextView = findViewById(R.id.signUpTextViewId);

        progressBar = findViewById(R.id.progressbarId);

        signUpTextView.setOnClickListener(this);
        loginInButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginInButton) {
            userLogin();
        } else if (view.getId() == R.id.signUpTextViewId) {
            Intent intent = new Intent(this, Signup.class);
            startActivity(intent);
        }
    }

    public void userLogin() {
        String email = loginInEmail.getText().toString().trim();
        String password = loginInPassword.getText().toString().trim();

        if (email.isEmpty()) {
            loginInEmail.setError("Enter an email address");
            loginInEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            loginInPassword.setError("Enter your password");
            loginInPassword.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginInEmail.setError("Enter a valid email address");
            loginInEmail.requestFocus();
            return;
        }

        if (password.length() < 6) {
            loginInPassword.setError("Minimum length of a password should be 6");
            loginInPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(getApplicationContext(), "Login is happening ", Toast.LENGTH_SHORT).show();

                if (task.isSuccessful()) {
                    Intent intent = new Intent(getApplicationContext(), DashBoard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void forgotButtonClicked(View view) {
        Intent intent = new Intent(this, Password_reset.class);
        startActivity(intent);
    }
}
