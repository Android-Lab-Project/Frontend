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

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        ImageSlider imageSlider = view.findViewById(R.id.slider);
        List<SlideModel> imageList = new ArrayList<SlideModel>();
        imageList.add(new SlideModel(R.drawable.doctor_model,"You Can Take Appointments of Top Class Doctors From Our App", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.diagnosis_image,"You Can Take Appointments of Important Diagnoses From Our App", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.medication2,"You Can Order Medicines From Our App", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.ai,"You Can Use Our Numerous AI Powered Features Also", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(imageList);
        return view;
    }
}