package com.example.myapplication;

public class Medicine_MyItem {

    private  String medicine_name;
    private String company_name;
    private  String medicine_price;

    public Medicine_MyItem(String medicine_name, String company_name, String medicine_price) {
        this.medicine_name = medicine_name;
        this.company_name = company_name;
        this.medicine_price = medicine_price;
    }

    public String getMedicine_name() {
        return medicine_name;
    }

    public String getCompany_name() {
        return company_name;
    }

    public String getMedicine_price() {
        return medicine_price;
    }


}
