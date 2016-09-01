package com.epicodus.checkup.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.epicodus.checkup.R;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends BaseActivity {
    @Bind(R.id.aboutTitle) TextView mAboutTitle;
    @Bind(R.id.aboutTextView) TextView mAboutTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        super.onCreateDrawer();
        ButterKnife.bind(this);

        Typeface questrialFont = Typeface.createFromAsset(getAssets(), "fonts/Questrial-Regular.otf");
        mAboutTitle.setTypeface(questrialFont);
        mAboutTextView.setTypeface(questrialFont);
    }
}
