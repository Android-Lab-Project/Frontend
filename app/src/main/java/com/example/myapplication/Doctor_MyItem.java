package com.example.myapplication;


public class Doctor_MyItem {

    private  Long id;
    private  String head;
    private String desc;
    private  String imageUrl;

    private String currentHospital;

    private Double onlineAvailableTime;

    private Double offlineAvailableTime;

    private String contactNumber;

    private String hospitalPlace;




    public Doctor_MyItem(String head, String desc, String imageUrl,Long id,String currentHospital,
    Double onlineAvailableTime,Double offlineAvailableTime,String contactNumber,String hospitalPlace) {
        this.head = head;
        this.desc=desc;
        this.imageUrl=imageUrl;
        this.id=id;
        this.currentHospital=currentHospital;
        this.onlineAvailableTime=onlineAvailableTime;
        this.offlineAvailableTime=offlineAvailableTime;
        this.contactNumber=contactNumber;
        this.hospitalPlace=hospitalPlace;

    }

    public String getHospitalPlace() {
        return hospitalPlace;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getCurrentHospital() {
        return currentHospital;
    }

    public Double getOnlineAvailableTime() {
        return onlineAvailableTime;
    }

    public Double getOfflineAvailableTime() {
        return offlineAvailableTime;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}
