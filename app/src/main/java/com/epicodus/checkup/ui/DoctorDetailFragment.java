package com.epicodus.checkup.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Doctor;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorDetailFragment extends Fragment {
    @Bind(R.id.doctorImageView) ImageView mImageLabel;
    @Bind(R.id.doctorNameTextView) TextView mNameLabel;
    @Bind(R.id.specialtyTextView) TextView mSpecialtyLabel;
    @Bind(R.id.websiteTextView) TextView mWebsiteLabel;
    @Bind(R.id.phoneTextView) TextView mPhoneLabel;
    @Bind(R.id.addressTextView) TextView mAddressLabel;
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
        mAddressLabel.setText(mDoctor.getAddress());
        mBioLabel.setText(mDoctor.getBio());

        return view;
    }

}
