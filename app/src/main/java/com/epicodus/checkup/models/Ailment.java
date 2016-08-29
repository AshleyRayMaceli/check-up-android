package com.epicodus.checkup.models;

import org.parceler.Parcel;
import java.util.Calendar;

@Parcel
public class Ailment {
    String title;
    String notes;
    String date;
    private String pushId;

    public Ailment() {}

    public Ailment(String title, String notes) {
        this.title = title;
        this.notes = notes;

        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";
        String month = (cal.get(Calendar.MONTH) + 1) + "";
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";

        this.date = month + " / " + day + " / " + year;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getDate() { return date; }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}