package com.epicodus.checkup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.epicodus.checkup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindDoctorActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.specialtySearchButton) Button mSpecialtySearchButton;
    @Bind(R.id.specialtyEditText) EditText mSpecialtyEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ButterKnife.bind(this);
        mSpecialtySearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSpecialtySearchButton) {
            String specialty = mSpecialtyEditText.getText().toString();
            Intent intent = new Intent(FindDoctorActivity.this, DoctorActivity.class);
            intent.putExtra("specialty", specialty);
            startActivity(intent);
        }
    }
}
