package com.epicodus.checkup.services;

import com.epicodus.checkup.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class BetterDoctorService {

    public static void findDoctorBySpecialty(String specialty, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        String url = Constants.BETTER_DOCTOR_BASE_URL + specialty + "&location=37.773%2C-122.413%2C100&user_location=37.773%2C-122.413&skip=0&limit=10&user_key=" + Constants.BETTER_DOCTOR_CONSUMER_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
