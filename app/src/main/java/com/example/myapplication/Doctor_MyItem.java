package com.example.myapplication;

public class Doctor_MyItem {
    private  String head;
    private String desc;
    private  String imageUrl;

    public Doctor_MyItem(String head, String desc, String imageUrl) {
        this.head = head;
        this.desc=desc;
        this.imageUrl=imageUrl;
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
}
