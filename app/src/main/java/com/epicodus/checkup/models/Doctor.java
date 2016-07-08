package com.epicodus.checkup.models;

import org.parceler.Parcel;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Parcel
public class Doctor {
    String mName;
    String mSpecialty;
    ArrayList<String> mPhone = new ArrayList<>();
    double mLatitude;
    double mLongitude;
    String mAddress;
    String mBio;

    public Doctor() {}

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
