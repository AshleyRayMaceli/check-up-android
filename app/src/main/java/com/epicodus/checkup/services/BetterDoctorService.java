package com.epicodus.checkup.services;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BetterDoctorService {

    public static void findDoctorsBySpecialty(String specialty, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.BETTER_DOCTOR_BASE_URL + specialty + "&location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=" + Constants.BETTER_DOCTOR_CONSUMER_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

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
                    String name = doctorJSON.getJSONObject("practices").getString("name");
                    String specialty = doctorJSON.getJSONObject("specialties").getString("actor");
                    String phone = doctorJSON.getJSONObject("phones").getString("number");
                    double latitude = doctorJSON.getJSONObject("practices").getDouble("lat");
                    double longitude = doctorJSON.getJSONObject("practices").getDouble("lon");

                    ArrayList<String> address = new ArrayList<>();
                    JSONArray addressJSON = doctorJSON.getJSONObject("practices").getJSONArray("visit_address");
                    for (int y = 0; y < addressJSON.length(); y++) {
                        address.add(addressJSON.get(y).toString());
                    }

                    String bio = doctorJSON.getString("bio");

                    Doctor doctor = new Doctor(name, specialty, phone, latitude, longitude, address, bio);
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
