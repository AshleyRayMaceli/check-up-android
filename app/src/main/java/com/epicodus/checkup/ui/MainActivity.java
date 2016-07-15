package com.epicodus.checkup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.epicodus.checkup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.appointmentsButton) Button mAppointmentsButton;
    @Bind(R.id.healthButton) Button mHealthButton;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAppointmentsButton.setOnClickListener(this);
        mHealthButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAppointmentsButton) {
            Intent intent = new Intent(MainActivity.this, AppointmentsActivity.class);
            startActivity(intent);
        } else if(v == mHealthButton) {
            Intent intent = new Intent(MainActivity.this, HealthActivity.class);
            startActivity(intent);
        } else if(v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        }
    }
}
