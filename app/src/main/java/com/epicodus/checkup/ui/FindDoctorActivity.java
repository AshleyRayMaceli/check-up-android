package com.epicodus.checkup.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FindDoctorActivity extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mCityPreference;
    private int mStateSelectedPosition;
    private int mSpecialistSelectedPosition;

    @Bind(R.id.specialtySearchButton) Button mSpecialtySearchButton;
    @Bind(R.id.cityEditText) TextView mCityEditText;
    @Bind(R.id.stateSpinner) Spinner mStateSpinner;
    @Bind(R.id.specialistSpinner) Spinner mSpecialistSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        ButterKnife.bind(this);
        mSpecialtySearchButton.setOnClickListener(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mCityPreference = mSharedPreferences.getString(Constants.PREFERENCES_CITY_KEY, null);

        if (mCityPreference != null) {
            mCityEditText.setText(mCityPreference);
            mStateSpinner.setSelection(mSharedPreferences.getInt(Constants.PREFERENCES_STATE_KEY, 0));
            mSpecialistSpinner.setSelection(mSharedPreferences.getInt(Constants.PREFERENCES_SPECIALTY_KEY, 0));
        }
    }

    @Override
    public void onClick(View v) {
        if(v == mSpecialtySearchButton) {
            String specialty = mSpecialistSpinner.getSelectedItem().toString().toLowerCase().replace(" ", "-");
            String city = mCityEditText.getText().toString().toLowerCase();
            String state = mStateSpinner.getSelectedItem().toString().toLowerCase();
            mStateSelectedPosition = mStateSpinner.getSelectedItemPosition();
            mSpecialistSelectedPosition = mSpecialistSpinner.getSelectedItemPosition();

            if (!(city).equals("")) {
                addToSharedPreferences(mSpecialistSelectedPosition, city, mStateSelectedPosition);
            }

            Intent intent = new Intent(FindDoctorActivity.this, DoctorListActivity.class);
            intent.putExtra("specialty", specialty);
            intent.putExtra("city", city);
            intent.putExtra("state", state);

            startActivity(intent);
        }
    }

    private void addToSharedPreferences(int specialty, String city, int state) {
        mEditor.putInt(Constants.PREFERENCES_SPECIALTY_KEY, specialty);
        mEditor.putString(Constants.PREFERENCES_CITY_KEY, city).apply();
        mEditor.putInt(Constants.PREFERENCES_STATE_KEY, state);
        mEditor.commit();
    }
}
