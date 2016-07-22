package com.epicodus.checkup.models;

import org.parceler.Parcel;

@Parcel
public class Ailment {
    String title;
    String notes;
    private String pushId;

    public Ailment() {}

    public Ailment(String title, String notes) {
        this.title = title;
        this.notes = notes;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
