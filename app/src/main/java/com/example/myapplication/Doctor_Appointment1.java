package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Doctor_Appointment1 extends AppCompatActivity {


    private TextView mTextViewDoctorName;
    private TextView mTextViewDoctorDesc;
    private ImageView mImageViewDoctorPhoto;

    private TextView mTextViewHospitalName;

    private TextView mTextViewHospitalPlace;

    private TextView mTextViewContactInfo;

   // private  static final String API_URL = "http://192.168.0.105:8080/add/appointment";

    private  static final String API_URL = StaticVariable.araf+"/add/appointment";

    Long idForDoctor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment1);
        Button bt = (Button) findViewById (R.id.getAppointment);

        mTextViewDoctorName=(TextView)findViewById(R.id.doctorName);
        mTextViewDoctorDesc=(TextView)findViewById(R.id.doctorTitle);
        mImageViewDoctorPhoto=(ImageView)findViewById(R.id.doctorPicture);
        mTextViewHospitalName=(TextView)findViewById(R.id.hospitalName);
        mTextViewHospitalPlace=(TextView)findViewById(R.id.hospitalAddress);
        mTextViewContactInfo=(TextView)findViewById(R.id.doctorPhone);



        //hello have to follow this
        Intent intent=getIntent();
        String inputDoctorName=intent.getStringExtra(Doctor_MyAdapter.key1);
        String inputDoctorDesc=intent.getStringExtra(Doctor_MyAdapter.key2);
        String inputDoctorPhoto=intent.getStringExtra(Doctor_MyAdapter.key3);
        String inputHospitalName=intent.getStringExtra(Doctor_MyAdapter.key4);
        String inputHospitalPlace=intent.getStringExtra(Doctor_MyAdapter.key5);
        String inputContactInfo=intent.getStringExtra(Doctor_MyAdapter.key6);

        Long inputDoctorId=intent.getLongExtra(Doctor_MyAdapter.key7,1);

        idForDoctor=inputDoctorId;
        StaticVariable.doctor_id=inputDoctorId;

        mTextViewDoctorName.setText(inputDoctorName);
        mTextViewDoctorDesc.setText("  " + inputDoctorDesc);

        mTextViewHospitalName.setText("  " + inputHospitalName);

        mTextViewHospitalPlace.setText("  " + inputHospitalPlace);
        mTextViewContactInfo.setText("  " + inputContactInfo);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAppoointment();
            //    makePostRequest();
            }
        });


    }
    public void openAppoointment() {
        Intent intent = new Intent(this, Docter_ConfirmAppointment.class);
        startActivity(intent);
    }

    public void makePostRequest() {
        JSONObject requestBody = new JSONObject();



        try {

            requestBody.put("doctorId",idForDoctor);
            requestBody.put("email", StaticVariable.email);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiRequestHelper.sendPostRequest(this, API_URL, requestBody, new ApiRequestHelper.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                // Handle the successful response here
                // 'result' contains the response from the server as a JSON string

//                try {
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


