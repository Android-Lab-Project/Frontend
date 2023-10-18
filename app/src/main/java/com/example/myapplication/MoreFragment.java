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
        View view = inflater.inflate(R.layout.fragment_more, container, false);


        Button bmiCalculatorButton = view.findViewById(R.id.bmiCalculator);
        bmiCalculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTargetActivity();
            }
        });

        return view;
    }


    public void openTargetActivity() {
        Intent intent = new Intent(getContext(), BMI_Calculator.class);
        startActivity(intent);
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
