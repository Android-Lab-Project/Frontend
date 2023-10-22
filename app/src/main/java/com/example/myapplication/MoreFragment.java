package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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


        CardView bmi = view.findViewById(R.id.bmi);
        CardView diagnosis = view.findViewById(R.id.diagnosis);
        CardView symptom = view.findViewById(R.id.symptom);
        CardView nutrition = view.findViewById(R.id.nutrition);
        CardView medication = view.findViewById(R.id.medication);
        CardView ai = view.findViewById(R.id.ai);

        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBMI();
            }
        });
        ai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAI();
            }
        });
        diagnosis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDiagnosis();
            }
        });


        symptom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSymptom();
            }
        });
        medication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMedication();
            }
        });
        nutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNutrition();
            }
        });
        return view;
    }


    public void openBMI() {
        Intent intent = new Intent(getContext(), BMI_Calculator.class);
        startActivity(intent);
    }
    public void openAI() {
        Intent intent = new Intent(getContext(), AIConsultation.class);
        startActivity(intent);
    }
    public void openDiagnosis() {
        Intent intent = new Intent(getContext(), Diagnosis.class);
        startActivity(intent);
    }
    public void openSymptom() {
        Intent intent = new Intent(getContext(), SymptomActivity.class);
        startActivity(intent);
    }
    public void openNutrition() {
        Intent intent = new Intent(getContext(), NutritionActivity.class);
        startActivity(intent);
    }
    public void openMedication() {
        Intent intent = new Intent(getContext(), MedicationActivity.class);
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
