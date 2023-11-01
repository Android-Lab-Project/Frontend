package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class Docter_ConfirmAppointment extends AppCompatActivity {


    private static final String TAG = "Docter_ConfirmAppointment";



    private  static final String API_URL2 = StaticVariable.araf+"/add/appointment";

    private TextView mDisplayDate;
    private EditText mEditTextViewPatientName;

    private EditText mEditTextViewPatientProblem;

    private EditText mEditTextViewPatientContactInfo;



    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private Button bt2 ;

    private Button bt3;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docter_confirm_appointment);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);

        bt2= (Button) findViewById (R.id.bookAppointment);

        bt3=(Button)findViewById(R.id.cancelAppointment343) ;

        mEditTextViewPatientName = (EditText) findViewById(R.id.patientFullName);
        mEditTextViewPatientProblem = (EditText) findViewById(R.id.patientProblem);
        mEditTextViewPatientContactInfo=(EditText)findViewById(R.id.patientContact) ;

//        String fullName = mEditTextViewPatientName.getText().toString();
//        StaticVariable.patient_name=fullName;
      //  patientProblem=mEditTextViewPatientProblem.getText().toString();

       // StaticVariable.patient_problem=patientProblem;




        //doctor id, patient email, problem, date

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Docter_ConfirmAppointment.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
               // Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                StaticVariable.patient_date=date;
                mDisplayDate.setText(date);
            }
        };


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullName = mEditTextViewPatientName.getText().toString();
                StaticVariable.patient_name=fullName;
                String patientProblem=mEditTextViewPatientProblem.getText().toString();
                 StaticVariable.patient_problem=patientProblem;

                String patient_ContactInfo=mEditTextViewPatientContactInfo.getText().toString().trim();
                StaticVariable.patient_contactInfo=patient_ContactInfo;

                openBook();
                makePostRequest2();
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                openPrevious();

            }
        });
    }

    public void openBook() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void openPrevious() {
        Intent intent = new Intent(this, DashBoard.class);
        startActivity(intent);
    }

    public void makePostRequest2() {
        JSONObject requestBody = new JSONObject();



        try {

            //profile,patientlist, AI]

            //picture add

            requestBody.put("doctorId",StaticVariable.doctor_id);
            requestBody.put("email", StaticVariable.email);
            requestBody.put("date", StaticVariable.patient_date);
            requestBody.put("userName",StaticVariable.patient_name);
            requestBody.put("problem",StaticVariable.patient_problem);
            requestBody.put("userPhone",StaticVariable.patient_contactInfo);
            //requestBody.put("problem",patientProblem);


        } catch (JSONException e) {
            e.printStackTrace();
        }

        //email,doctor_id,date, doctorname,doctorpic

     //   date, doctorname,doctorpic-> have to show that

        // date, doctorname,doctorpic-> string



        ApiRequestHelper.sendPostRequest(this, API_URL2, requestBody, new ApiRequestHelper.VolleyCallback() {
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




}

/*
@Override
public void onSuccess(String result) {
    try {
        // Parse the JSON response
        JSONObject jsonResponse = new JSONObject(result);

        // Check if the response contains the doctor's name, image, and date fields
        if (jsonResponse.has("doctorName") && jsonResponse.has("doctorImage") && jsonResponse.has("date")) {
            // Extract data from the JSON response
            String doctorName = jsonResponse.getString("doctorName");
            String doctorImage = jsonResponse.getString("doctorImage");
            String date = jsonResponse.getString("date");

            // Now you have the doctor's name, image, and date
            // You can use these values as needed

            // For example, you can display the doctor's name and date in TextViews
            TextView doctorNameTextView = findViewById(R.id.doctorNameTextView);
            TextView dateTextView = findViewById(R.id.dateTextView);
            doctorNameTextView.setText("Doctor: " + doctorName);
            dateTextView.setText("Date: " + date);

            // You can also load and display the doctor's image using a library like Picasso or Glide
            ImageView doctorImageView = findViewById(R.id.doctorImageView);
            // Load the doctor's image using a library like Picasso
            Picasso.get().load(doctorImage).into(doctorImageView);
        } else {
            // Handle the case where the expected keys are not present in the response
        }
    } catch (JSONException e) {
        e.printStackTrace();
        // Handle JSON parsing error, if any
    }
}

 */
/*
//                    // Parse the JSON response
//                    JSONObject jsonResponse = new JSONObject(result);
//
//                    // Check if the response contains a specific key
//                    if (jsonResponse.has("key")) {
//                        // Extract data from the JSON response
//                        String data = jsonResponse.getString("key");
//
//                        // Now you can use the 'data' as needed
//                        // For example, update UI elements or perform other actions
//                    } else {
//                        // Handle the case where the expected key is not present in the response
//                    }
 */