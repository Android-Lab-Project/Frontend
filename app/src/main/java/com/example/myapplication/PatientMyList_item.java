package com.example.myapplication;

import android.widget.Button;

public class PatientMyList_item {

    private String patientName;
    private String appointmentDate_for;

    private  String patientProblem;

    private String patientContactInformation;

    private Long id;



    public PatientMyList_item(String patientName, String apppointmentDate_for, String patientProblem,String contact,Long id) {
        this.patientName = patientName;
        this.appointmentDate_for = apppointmentDate_for;
        this.patientProblem = patientProblem;
        this.patientContactInformation=contact;
        this.id=id;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getApppointmentDate_for() {
        return appointmentDate_for;
    }

    public String getPatientProblem() {
        return patientProblem;
    }


    public String getPatientContactInformation() {
        return patientContactInformation;
    }

    public Long getId() {
        return id;
    }
}
