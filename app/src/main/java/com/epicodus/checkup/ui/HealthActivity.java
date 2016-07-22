package com.epicodus.checkup.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Ailment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.ailmentTitleEditText) EditText mAilmentTitleEditText;
    @Bind(R.id.ailmentNotesEditText) EditText mAilmentNotesEditText;
    @Bind(R.id.addAilmentButton) Button mAddAilmentButton;
    @Bind(R.id.ailmentsListView) ListView mAilmentsListView;

    private ArrayList<String> allUserAilments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);
        ButterKnife.bind(this);
        mAddAilmentButton.setOnClickListener(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, allUserAilments);
        mAilmentsListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v == mAddAilmentButton) {
            String ailmentTitle = mAilmentTitleEditText.getText().toString();
            String ailmentNotes = mAilmentNotesEditText.getText().toString();

            Ailment ailment = new Ailment(ailmentTitle, ailmentNotes);

            DatabaseReference ailmentRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_AILMENTS);

            DatabaseReference pushRef = ailmentRef.push();
            String pushId = pushRef.getKey();
            ailment.setPushId(pushId);
            pushRef.setValue(ailment);

            allUserAilments.add(ailmentTitle);

            Toast.makeText(this, "Save Successful!", Toast.LENGTH_SHORT).show();
            mAilmentTitleEditText.setText("");
            mAilmentNotesEditText.setText("");
        }
    }
}
