package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class DoctorSignUp extends AppCompatActivity implements View.OnClickListener {

    private EditText DoctorSignUpName,DoctorSignUpEmail,DoctorSignUpPassword,DoctorSignUpContactNumber;
    private EditText DoctorDegree,DoctorHospitalName;
    private Button DoctorSignUpButton;

    private FirebaseAuth mAuth;

    private ProgressBar progressBar;

    private  static final String API_URL3 = StaticVariable.araf+"/register/doctor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

       // Button btSignDoctor = (Button) findViewById (R.id.signup2);


        mAuth=FirebaseAuth.getInstance();

        progressBar=findViewById(R.id.progressbarId2);

        DoctorSignUpName=findViewById(R.id.name2);
        DoctorSignUpEmail=findViewById(R.id.email2);
       DoctorSignUpPassword=findViewById(R.id.password2);
        DoctorSignUpContactNumber=findViewById(R.id.phone_number2);
        DoctorDegree=findViewById(R.id.doctor_degree);
        DoctorHospitalName=findViewById(R.id.hospitalDoctor);

        DoctorSignUpButton=findViewById(R.id.signup2);

       DoctorSignUpButton.setOnClickListener(this);



//        btSignDoctor.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                openDoctorDashBoard();
//            }
//        });

    }


    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.signup2){
            userRegister();

        }
        //else if(view.getId()==R.id.signInTextId){
//            Intent intent=new Intent(getApplicationContext(),Login.class);
//            startActivity(intent);
//        }

    }

    public  void userRegister(){

        String  name=DoctorSignUpName.getText().toString().trim();
        //String  email=signUpEmail.getText().toString().trim();
        String email = DoctorSignUpEmail.getText().toString().trim();
        String  password=DoctorSignUpPassword.getText().toString().trim();
        String  phone_number=DoctorSignUpContactNumber.getText().toString().trim();

        String doctor_deg=DoctorDegree.getText().toString().trim();
        String doctor_hospital= DoctorHospitalName.getText().toString().trim();

        StaticVariable.doctor_name=name;
        StaticVariable.doctor_email=email;
        StaticVariable.doctor_password=password;
        StaticVariable.doctor_contact=phone_number;
        StaticVariable.doctor_degree=doctor_deg;
        StaticVariable.doctor_hospital=doctor_hospital;


        if(name.isEmpty()){
            DoctorSignUpName.setError("Enter your name");
            DoctorSignUpName.requestFocus();
            return;
        }
        if(email.isEmpty()){
            DoctorSignUpEmail.setError("Enter an email Address");
            DoctorSignUpEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            DoctorSignUpPassword.setError("Enter your password");
            DoctorSignUpPassword.requestFocus();
            return;
        }
        if(phone_number.isEmpty()){
            DoctorSignUpContactNumber.setError("Enter your phone Number");
            DoctorSignUpContactNumber.requestFocus();
            return;
        }



//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            signUpEmail.setError("Enter a valid email address");
//            signUpEmail.requestFocus();
//            return;
//        }

        if(password.length()<6){
            DoctorSignUpEmail.setError("Minimum length of a password should be 6");
            DoctorSignUpEmail.requestFocus();
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

                    makePostRequestDoctor();
                    //finish();


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

    public void makePostRequestDoctor() {


        JSONObject requestBody = new JSONObject();



        try {

            //profile,patientlist, AI]


            //picture add

          //  HashMap<String,String>mp=new HashMap<>();

            requestBody.put("firstName",StaticVariable.doctor_name);
            requestBody.put("email",StaticVariable.doctor_email);
            requestBody.put("password",StaticVariable.doctor_password);
            requestBody.put("contactNo",StaticVariable.doctor_contact);

         //   requestBody.put("appUser",mp);


            requestBody.put("currentHospital", StaticVariable.doctor_hospital);
            requestBody.put("degrees", StaticVariable.doctor_degree);




            Intent intent = new Intent(getApplicationContext(), DoctorDashboard.class);
            //  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            // finish();


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //email,doctor_id,date, doctorname,doctorpic

        //   date, doctorname,doctorpic-> have to show that

        // date, doctorname,doctorpic-> string



        ApiRequestHelper.sendPostRequest(this, API_URL3, requestBody, new ApiRequestHelper.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

//                try {
//                    // Parse the JSON response
//                    JSONObject jsonResponse = new JSONObject(result);
//
//                    // Check if the response contains the doctor's name, image, and date fields
//                    if (jsonResponse.has("doctorName") && jsonResponse.has("doctorPic") && jsonResponse.has("date")) {
//                        // Extract data from the JSON response
//                        String doctorName = jsonResponse.getString("doctorName");
//                        String doctorImage = jsonResponse.getString("doctorPic");
//                        String date = jsonResponse.getString("date");
//
//
//                    } else {
//                        // Handle the case where the expected keys are not present in the response
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    // Handle JSON parsing error, if any
//                }


            }

            @Override
            public void onError(VolleyError error) {

                // Toast.makeText(context, "Server Error for apiRequest!", Toast.LENGTH_SHORT).show();
                // Handle errors here
                // 'error' contains information about the error
            }
        });
    }




    public void signInClick(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }





    public void openDoctorDashBoard() {
        Intent intent = new Intent(this, DoctorDashboard.class);
        startActivity(intent);
    }
}