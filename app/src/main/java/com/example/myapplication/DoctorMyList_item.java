package com.example.myapplication;

public class DoctorMyList_item {

    private String doctor_name;
    private String doctor_pic;
    private String apppointment_date;

    public DoctorMyList_item(String doctor_name, String doctor_pic, String apppointment_date) {
        this.doctor_name = doctor_name;
        this.doctor_pic = doctor_pic;
        this.apppointment_date = apppointment_date;
    }

    public String getDoctor_name() {
        return doctor_name;
    }

    public String getDoctor_pic() {
        return doctor_pic;
    }

    public String getApppointment_date() {
        return apppointment_date;
    }
}
