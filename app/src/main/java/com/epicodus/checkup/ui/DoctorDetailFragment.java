package com.epicodus.checkup.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Doctor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.doctorNameTextView) TextView mNameLabel;
    @Bind(R.id.specialtyTextView) TextView mSpecialtyLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.streetTextView) TextView mStreetLabel;
    @Bind(R.id.cityStateTextView) TextView mCityStateLabel;
    @Bind(R.id.bioTextView) TextView mBioLabel;
    @Bind(R.id.saveDoctorButton) Button mSaveDoctorButton;

    private Doctor mDoctor;

    public static DoctorDetailFragment newInstance (Doctor doctor) {
        DoctorDetailFragment doctorDetailFragment = new DoctorDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("doctor", Parcels.wrap(doctor));
        doctorDetailFragment.setArguments(args);
        return doctorDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDoctor = Parcels.unwrap(getArguments().getParcelable("doctor"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_detail, container, false);
        ButterKnife.bind(this, view);

        mNameLabel.setText(mDoctor.getName());
        mSpecialtyLabel.setText(mDoctor.getSpecialty());
        mPhoneLabel.setText(android.text.TextUtils.join(", ", mDoctor.getPhone()));
        mStreetLabel.setText(mDoctor.getStreet() + " " + mDoctor.getStreet2());
        mCityStateLabel.setText(mDoctor.getCity() + ", " + mDoctor.getState() + ", " + mDoctor.getZip());
        mBioLabel.setText(mDoctor.getBio());

        mStreetLabel.setOnClickListener(this);
        mCityStateLabel.setOnClickListener(this);
        mSaveDoctorButton.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mStreetLabel || v == mCityStateLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + mDoctor.getLatitude() + "," + mDoctor.getLongitude() + "?q=(" + mDoctor.getName() + ")"));
            startActivity(mapIntent);
        }
        if (v == mSaveDoctorButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference doctorRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_DOCTORS)
                    .child(uid);

            DatabaseReference pushRef = doctorRef.push();
            String pushId = pushRef.getKey();
            mDoctor.setPushId(pushId);
            pushRef.setValue(mDoctor);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
