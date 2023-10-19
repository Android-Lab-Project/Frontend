package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        Button diagnosisButton = view.findViewById(R.id.diagnosis);
        diagnosisButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openDiagnosisActivity();
            }
        });



        return view;


    }

    public void openDiagnosisActivity() {
        Intent intent = new Intent(getContext(), Diagnosis.class);
        startActivity(intent);
    }


}