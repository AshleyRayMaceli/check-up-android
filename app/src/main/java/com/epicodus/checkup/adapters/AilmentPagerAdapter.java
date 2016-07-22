package com.epicodus.checkup.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.epicodus.checkup.models.Ailment;
import com.epicodus.checkup.ui.AilmentDetailFragment;

import java.util.ArrayList;

public class AilmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Ailment> mAilments;

    public AilmentPagerAdapter(FragmentManager fm, ArrayList<Ailment> ailments) {
        super(fm);
        mAilments = ailments;
    }

    @Override
    public Fragment getItem(int position) {
        return AilmentDetailFragment.newInstance(mAilments.get(position));
    }

    @Override
    public int getCount() {
        return mAilments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mAilments.get(position).getTitle();
    }
}
