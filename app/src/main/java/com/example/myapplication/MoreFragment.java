package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MoreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_more, container, false);

        EditText edtWeight,edtHeightFT,edtHeightIn;
        Button btnCalculate;
        TextView txtResult;
        RelativeLayout rrMain;

        edtWeight=(EditText) view.findViewById(R.id.edtWeight);
         edtHeightFT=(EditText) view.findViewById(R.id.edtHeightFt);
         edtHeightIn=(EditText) view.findViewById(R.id.edtHeightIn);

         btnCalculate=(Button) view.findViewById(R.id.btnCalculate);

         txtResult=(TextView) view.findViewById(R.id.txtResult);

         rrMain=(RelativeLayout) view.findViewById(R.id.RRlMain);

         btnCalculate.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View v){
                 int wt=Integer.parseInt(edtWeight.getText().toString());
                 int ft=Integer.parseInt(edtHeightFT.getText().toString());
                 int in=Integer.parseInt(edtHeightIn.getText().toString());


                 int totalIn=ft*12+in;

                 double totalCm=totalIn*2.54;

                 double totalMeter= totalCm/100;

                 double bmi=wt/(totalMeter*totalMeter);



                 if (bmi > 25 && bmi < 30) {
                     txtResult.setText("You are OverWeight");
                     rrMain.setBackgroundColor(getResources().getColor(R.color.colorOW));
                 } else if (bmi < 18) {
                     txtResult.setText("You are UnderWeight");
                     rrMain.setBackgroundColor(getResources().getColor(R.color.colorUW));
                 } else if (bmi >= 18 && bmi <= 25) {
                     txtResult.setText("You are Healthy");
                     rrMain.setBackgroundColor(getResources().getColor(R.color.colorHealthy));
                 } else {
                     txtResult.setText("You have Obesity.");
                     rrMain.setBackgroundColor(getResources().getColor(R.color.colorOOW));
                 }





             }
         });



        return  view;
    }

    public void moreClicked(View view) {


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:")); // This ensures only messaging apps are shown
        intent.putExtra("sms_body", "Hello, I'm using the messaging app!");

        if (getContext() != null) {
            if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }





}