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
    String street;
    String street2;
    String city;
    String state;
    String zip;
    String bio;
    private String pushId;

    public Doctor() {}

    public Doctor(String name, String specialty, ArrayList<String> phone, double latitude, double longitude, String street, String street2, String city, String state, String zip, String bio) {
        this.name = name;
        this.specialty = specialty;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.street = street;
        this.street2 = street2;
        this.city = city;
        this.state = state;
        this.zip = zip;
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

    public String getStreet() { return street; }

    public String getStreet2() { return street2; }

    public String getCity() { return city; }

    public String getState() { return state; }

    public String getZip() { return zip; }

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
