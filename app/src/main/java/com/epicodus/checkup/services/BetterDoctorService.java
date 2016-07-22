package com.epicodus.checkup.services;

import android.telephony.PhoneNumberUtils;
import android.util.Log;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BetterDoctorService {

    public static void findDoctorsByLocationAndSpecialty(String specialty, String city, String state, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String locationSlug = state + "-" + city;

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BETTER_DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_SPECIALTY_QUERY_PARAMETER, specialty);
        urlBuilder.addQueryParameter(Constants.BETTER_DOCTOR_LOCATION_QUERY_PARAMETER, locationSlug);
        urlBuilder.addQueryParameter(Constants.API_KEY_QUERY_PARAMETER, Constants.BETTER_DOCTOR_CONSUMER_KEY);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Log.d("URL", request + "");

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject betterDoctorJSON = new JSONObject(jsonData);
                JSONArray resultsJSON = betterDoctorJSON.getJSONArray("data");
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject doctorJSON = resultsJSON.getJSONObject(i);
                    String name = doctorJSON.getJSONArray("practices").getJSONObject(0).getString("name");
                    String specialty = doctorJSON.getJSONArray("specialties").getJSONObject(0).getString("actor");

                    ArrayList<String> phone = new ArrayList<>();
                    JSONArray phoneJSON = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONArray("phones");
                    for (int y = 0; y < phoneJSON.length(); y++) {
                        String number = phoneJSON.getJSONObject(y).getString("number");
                        String formattedNumber = PhoneNumberUtils.formatNumber(number, Locale.getDefault().getCountry());
                        phone.add(formattedNumber);
                    }

                    double latitude = doctorJSON.getJSONArray("practices").getJSONObject(0).getDouble("lat");
                    double longitude = doctorJSON.getJSONArray("practices").getJSONObject(0).getDouble("lon");
                    String street = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street");

                    String street2;
                    try {
                        street2 = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("street2");
                    } catch (JSONException e) {
                        street2 = "";
                    }

                    String city = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("city");
                    String state = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("state");
                    String zip = doctorJSON.getJSONArray("practices").getJSONObject(0).getJSONObject("visit_address").getString("zip");
                    String bio = doctorJSON.getJSONObject("profile").getString("bio");

                    Doctor doctor = new Doctor(name, specialty, phone, latitude, longitude, street, street2, city, state, zip, bio);
                    doctors.add(doctor);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
