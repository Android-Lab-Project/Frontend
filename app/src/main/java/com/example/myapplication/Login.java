package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private EditText loginInEmail, loginInPassword;
    private TextView signUpTextView;
    private Button loginInButton;
    ProgressBar progressBar;
    Context context;
    ProgressDialog progressDialog;
    String url; // Move the URL declaration here

    private FirebaseAuth mAuth;

   // private static final String API_URL4 = StaticVariable.araf +"/doctor/verify?email="+StaticVariable.email;

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

        context = this; // Initialize the context here
      //  url =StaticVariable.araf +"/doctor/verify?email="+StaticVariable.email;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.loginInButton) {
            userLogin();

        } else if (view.getId() == R.id.signUpTextViewId) {
            Intent intent = new Intent(this, chooseOption.class);
            startActivity(intent);
        }
    }

    public void userLogin() {
        String email = loginInEmail.getText().toString().trim();
        String password = loginInPassword.getText().toString().trim();

        StaticVariable.email = email;


        url =StaticVariable.araf +"/doctor/verify?email="+StaticVariable.email;

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
            loginInPassword.setError("Minimum password length should be 6");
            loginInPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Login is happening", Toast.LENGTH_SHORT).show();
                if (task.isSuccessful()) {
                    loadData();
                } else {
                    Toast.makeText(getApplicationContext(), "Login unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Intent intent;
                    if ("Yes".equals(jsonObject.getString("data"))) {
                        intent = new Intent(context, DoctorDashboard.class);
                        Toast.makeText(context, "Login successful For Doctor", Toast.LENGTH_SHORT).show();
                    }
                    else if("No".equals(jsonObject.getString("data"))) {
                        intent = new Intent(context, DashBoard.class);
                        Toast.makeText(context, "Login successful For User", Toast.LENGTH_SHORT).show();
                    }
                    else{

                        intent = new Intent(context, DoctorDashboard.class);
                        Toast.makeText(context, "Login successful by default ", Toast.LENGTH_SHORT).show();
                    }

                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Login error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(stringRequest);
    }

    public void forgotButtonClicked(View view) {
        Intent intent = new Intent(this, Password_reset.class);
        startActivity(intent);
    }
}
