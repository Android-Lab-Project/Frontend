package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Doctor_Appointment1 extends AppCompatActivity {


    private TextView mTextViewDoctorName;
    private TextView mTextViewDoctorDesc;
    private ImageView mImageViewDoctorPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_appointment1);
        Button bt = (Button) findViewById (R.id.getAppointment);

        mTextViewDoctorName=(TextView)findViewById(R.id.doctorName);
        mTextViewDoctorDesc=(TextView)findViewById(R.id.doctorTitle);
        mImageViewDoctorPhoto=(ImageView)findViewById(R.id.doctorPicture);

        Intent intent=getIntent();
        String inputDoctorName=intent.getStringExtra(Doctor_MyAdapter.key1);
        String inputDoctorDesc=intent.getStringExtra(Doctor_MyAdapter.key2);
        String inputDoctorPhoto=intent.getStringExtra(Doctor_MyAdapter.key3);

       // int imageResource = getResources().getIdentifier(inputDoctorPhoto, "drawable", getPackageName());

        mTextViewDoctorName.setText(inputDoctorName);
        mTextViewDoctorDesc.setText(inputDoctorDesc);
      //  mImageViewDoctorPhoto.setImageResource(imageResource);

        Picasso.get().load(inputDoctorPhoto).into(mImageViewDoctorPhoto);


        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAppoointment();
            }
        });


    }
    public void openAppoointment() {
        Intent intent = new Intent(this, Docter_ConfirmAppointment.class);
        startActivity(intent);
    }


}


