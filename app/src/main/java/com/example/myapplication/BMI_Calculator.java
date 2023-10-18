package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BMI_Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);


        EditText edtWeight,edtHeightFT,edtHeightIn;
        Button btnCalculate;
        TextView txtResult;
        RelativeLayout rrMain;


        edtWeight=(EditText) findViewById(R.id.edtWeight);
        edtHeightFT=(EditText) findViewById(R.id.edtHeightFt);
        edtHeightIn=(EditText) findViewById(R.id.edtHeightIn);

        btnCalculate=(Button) findViewById(R.id.btnCalculate);

        txtResult=(TextView) findViewById(R.id.txtResult);

      //  rrMain=(RelativeLayout) findViewById(R.id.RRlMain);
        rrMain=(RelativeLayout)findViewById(R.id.RRlMain);

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



    }
}