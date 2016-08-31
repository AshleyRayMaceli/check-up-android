package com.epicodus.checkup.ui;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Ailment;

import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AilmentDetailFragment extends Fragment {
    @Bind(R.id.ailmentTitleTextView) TextView mAilmentTitleTextView;
    @Bind(R.id.ailmentNotesTextView) TextView mAilmentNotesTextView;
    @Bind(R.id.dateTextView) TextView mDateTextView;
    @Bind(R.id.firstNoticedTextView) TextView mFirstNoticedTextView;

    private Ailment mAilment;

    public static AilmentDetailFragment newInstance(Ailment ailment) {
        AilmentDetailFragment ailmentDetailFragment = new AilmentDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("ailment", Parcels.wrap(ailment));
        ailmentDetailFragment.setArguments(args);
        return ailmentDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAilment = Parcels.unwrap(getArguments().getParcelable("ailment"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ailment_detail, container, false);
        ButterKnife.bind(this, view);

        mAilmentTitleTextView.setText(mAilment.getTitle());
        mAilmentNotesTextView.setText(mAilment.getNotes());
        mDateTextView.setText(mAilment.getDate());

        Typeface questrialFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Questrial-Regular.otf");
        mAilmentTitleTextView.setTypeface(questrialFont);
        mAilmentNotesTextView.setTypeface(questrialFont);
        mDateTextView.setTypeface(questrialFont);
        mFirstNoticedTextView.setTypeface(questrialFont);

        return view;
    }
}
