package com.epicodus.checkup.models;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Parcel
public class Doctor {
    String name;
    String specialty;
    ArrayList<String> phone = new ArrayList<>();
    double latitude;
    double longitude;
    String address;
    String bio;
    private String pushId;

    public Doctor() {}

    public Doctor(String name, String specialty, ArrayList<String> phone, double latitude, double longitude, String address, String bio) {
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public ArrayList<String> getPhone() {
        return phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getAddress() {
        return address;
    }

    public String getBio() {
        return bio;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

}
