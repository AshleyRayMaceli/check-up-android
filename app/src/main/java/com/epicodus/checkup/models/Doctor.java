package com.epicodus.checkup.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Doctor {
    private String mName;
    private String mSpecialty;
    private ArrayList<String> mPhone = new ArrayList<>();
    private double mLatitude;
    private double mLongitude;
    private String mAddress;
    private String mBio;

    public Doctor(String name, String specialty, ArrayList<String> phone, double latitude, double longitude, String address, String bio) {
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

    public ArrayList<String> getPhone() {
        return mPhone;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public String getAddress() {
        return mAddress;
    }

    public String getBio() {
        return mBio;
    }

}
