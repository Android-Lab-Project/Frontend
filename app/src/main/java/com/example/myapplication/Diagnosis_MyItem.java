package com.example.myapplication;

public class Diagnosis_MyItem {

    private String diagnosis_name;
    private String hospital_name;
    private String diagnosis_price;

    public Diagnosis_MyItem(String diagnosis_name, String hospital_name, String diagnosis_price) {
        this.diagnosis_name = diagnosis_name;
        this.hospital_name = hospital_name;
        this.diagnosis_price = diagnosis_price;
    }

    public String getDiagnosis_name() {
        return diagnosis_name;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public String getDiagnosis_price() {
        return diagnosis_price;
    }
}
