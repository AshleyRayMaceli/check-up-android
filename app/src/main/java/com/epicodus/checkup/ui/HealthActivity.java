package com.epicodus.checkup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Ailment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthActivity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.ailmentTitleEditText) EditText mAilmentTitleEditText;
    @Bind(R.id.ailmentNotesEditText) EditText mAilmentNotesEditText;
    @Bind(R.id.addAilmentButton) Button mAddAilmentButton;
    @Bind(R.id.allAilmentsButton) Button mAllAilmentsButton;
    @Bind(R.id.ailmentFormTitle) TextView mAilmentFormTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        super.onCreateDrawer();
        ButterKnife.bind(this);

        Typeface questrialFont = Typeface.createFromAsset(getAssets(), "fonts/Questrial-Regular.otf");
        mAilmentFormTitle.setTypeface(questrialFont);

        mAddAilmentButton.setOnClickListener(this);
        mAllAilmentsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mAddAilmentButton) {
            String ailmentTitle = mAilmentTitleEditText.getText().toString();
            String ailmentNotes = mAilmentNotesEditText.getText().toString();

            Ailment ailment = new Ailment(ailmentTitle, ailmentNotes);

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();

            DatabaseReference ailmentRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_AILMENTS)
                    .child(uid);

            DatabaseReference pushRef = ailmentRef.push();
            String pushId = pushRef.getKey();
            ailment.setPushId(pushId);
            pushRef.setValue(ailment);

            Toast.makeText(this, "Save Successful!", Toast.LENGTH_SHORT).show();
            mAilmentTitleEditText.setText("");
            mAilmentNotesEditText.setText("");
        }

        if(v == mAllAilmentsButton) {
            Intent intent = new Intent(HealthActivity.this, AilmentsListActivity.class);
            startActivity(intent);
        }
    }
}
