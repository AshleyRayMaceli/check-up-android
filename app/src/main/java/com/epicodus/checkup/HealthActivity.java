package com.epicodus.checkup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HealthActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.newAilmentInput) EditText mNewAilmentInput;
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
            String ailment = mNewAilmentInput.getText().toString();
            allUserAilments.add(ailment);
            mNewAilmentInput.setText("");
        }
    }
}
