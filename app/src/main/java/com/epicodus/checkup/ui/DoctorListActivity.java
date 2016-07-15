package com.epicodus.checkup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;
import com.epicodus.checkup.adapters.DoctorListAdapter;
import com.epicodus.checkup.models.Doctor;
import com.epicodus.checkup.services.BetterDoctorService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoctorListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();
    private DoctorListAdapter mAdapter;

    private SharedPreferences mSharedPreferences;
    private String mRecentSpecialty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String specialty = intent.getStringExtra("specialty");
        String location = intent.getStringExtra("location");
        getDoctors(specialty, location);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentSpecialty = mSharedPreferences.getString(Constants.PREFERENCES_SPECIALTY_KEY, null);
        Log.d("Shared Pref Location", mRecentSpecialty);
    }

    private void getDoctors(String specialty, String location) {
        final BetterDoctorService betterDoctorService = new BetterDoctorService();

        betterDoctorService.findDoctorsByLocationAndSpecialty(specialty, location, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                mDoctors = betterDoctorService.processResults(response);

                DoctorListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DoctorListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
