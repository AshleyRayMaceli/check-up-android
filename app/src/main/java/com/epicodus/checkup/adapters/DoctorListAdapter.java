package com.epicodus.checkup.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Doctor;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorListAdapter extends RecyclerView.Adapter<DoctorListAdapter.DoctorViewHolder> {
    private ArrayList<Doctor> mDoctors = new ArrayList<>();
    private Context mContext;

    public DoctorListAdapter(Context context, ArrayList<Doctor> doctors) {
        mContext = context;
        mDoctors = doctors;
    }

    @Override
    public DoctorListAdapter.DoctorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_list_item, parent, false);
        DoctorViewHolder viewHolder = new DoctorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorListAdapter.DoctorViewHolder holder, int position) {
        holder.bindDoctor(mDoctors.get(position));
    }

    @Override
    public int getItemCount() {
        return mDoctors.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.doctorImageView) ImageView mDoctorImageView;
        @Bind(R.id.doctorNameTextView) TextView mDoctorNameTextView;
        @Bind(R.id.specialtyTextView) TextView mSpecialtyTextView;

        private Context mContext;

        public DoctorViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindDoctor(Doctor doctor) {
            mDoctorNameTextView.setText(doctor.getName());
            mSpecialtyTextView.setText(doctor.getSpecialty());
        }
    }
}
