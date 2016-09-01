package com.epicodus.checkup.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.epicodus.checkup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AppointmentsActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.appointmentsListView) ListView mAppointmentsListView;
    @Bind(R.id.newAppointmentButton) Button mNewAppointmentButton;
    @Bind(R.id.findDoctorButton) Button mFindDoctorButton;
    @Bind(R.id.savedDoctorsButton) Button mSavedDoctorsButton;

    private String[] appointments = new String[] {"Doctor A - Jan 1st 2017", "Doctor B - Feb 5th 2017", "Doctor C - Feb 19th 2017", "Doctor D - March 1st 2017"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        super.onCreateDrawer();

        ButterKnife.bind(this);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, appointments);
        mAppointmentsListView.setAdapter(adapter);
        mNewAppointmentButton.setOnClickListener(this);
        mFindDoctorButton.setOnClickListener(this);
        mSavedDoctorsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mNewAppointmentButton) {
            Intent intent = new Intent(AppointmentsActivity.this, AppointmentFormActivity.class);
            startActivity(intent);
        }
        if (v == mFindDoctorButton) {
            Intent intent = new Intent(AppointmentsActivity.this, FindDoctorActivity.class);
            startActivity(intent);
        }
        if (v == mSavedDoctorsButton) {
            Intent intent = new Intent(AppointmentsActivity.this, SavedDoctorListActivity.class);
            startActivity(intent);
        }
    }
}
