package com.epicodus.checkup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Doctor;
import com.epicodus.checkup.services.BetterDoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    public static final String TAG = DoctorActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String specialty = intent.getStringExtra("specialty");
        getDoctors(specialty);
    }

    private void getDoctors(String specialty) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();

        betterDoctorService.findDoctorsBySpecialty(specialty, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = betterDoctorService.processResults(response);

                DoctorActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] doctorNames = new String[mDoctors.size()];
                        for (int i = 0; i < doctorNames.length; i++) {
                            doctorNames[i] = mDoctors.get(i).getName();
                        }

                        ArrayAdapter adapter = new ArrayAdapter(DoctorActivity.this, android.R.layout.simple_list_item_1, doctorNames);
                        mListView.setAdapter(adapter);

                        for (Doctor doctor : mDoctors) {
                            Log.d(TAG, "Name: " + doctor.getName());
                            Log.d(TAG, "Specialty: " + doctor.getSpecialty());
                            Log.d(TAG, "Phone: " + doctor.getPhone());
                            Log.d(TAG, "Lat: " + Double.toString(doctor.getLatitude()));
                            Log.d(TAG, "Lon: " + Double.toString(doctor.getLongitude()));
                            Log.d(TAG, "Address: " + doctor.getAddress());
                            Log.d(TAG, "Bio: " + doctor.getBio());
                        }
                    }
                });
            }
        });
    }
}
