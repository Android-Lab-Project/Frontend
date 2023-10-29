package com.example.myapplication;

public class PatientMyList_item {

    private String patientName;
    private String appointmentDate_for;
    private  String patientProblem;

    private String patientContactInformation;

    public PatientMyList_item(String patientName, String apppointmentDate_for, String patientProblem,String contact) {
        this.patientName = patientName;
        this.appointmentDate_for = apppointmentDate_for;
        this.patientProblem = patientProblem;
        this.patientContactInformation=contact;
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
}
