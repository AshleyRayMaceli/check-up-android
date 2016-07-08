package com.epicodus.checkup.models;

import java.util.ArrayList;

public class Doctor {
    private String mName;
    private String mSpecialty;
    private String mPhone;
    private double mLatitude;
    private double mLongitude;
    private ArrayList<String> mAddress = new ArrayList<>();
    private String mBio;

    public Doctor(String name, String specialty, String phone, double latitude, double longitude, ArrayList<String> address, String bio) {
        this.mName = name;
        this.mSpecialty = specialty;
        this.mPhone = phone;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mAddress = address;
        this.mBio = bio;
    }

    public String getName() {
        return mName;
    }

    public String getSpecialty() {
        return mSpecialty;
    }

    public String getPhone() {
        return mPhone;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public ArrayList<String> getAddress() {
        return mAddress;
    }

    public String getBio() {
        return mBio;
    }

}
