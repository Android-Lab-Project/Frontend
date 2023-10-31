package com.example.myapplication;

import java.util.Date;

public class Reminders {
    public Reminders(String message, Date remindDate) {
        this.message = message;
        this.remindDate = remindDate;
    }

    private String message;
    private Date  remindDate;

    public String getMessage() {
        return message;
    }

    public Date getRemindDate() {
        return remindDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setRemindDate(Date remindDate) {
        this.remindDate = remindDate;
    }
}