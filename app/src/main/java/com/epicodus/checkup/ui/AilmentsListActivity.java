package com.epicodus.checkup.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.epicodus.checkup.Constants;
import com.epicodus.checkup.R;
import com.epicodus.checkup.adapters.AllAilmentsListAdapter;
import com.epicodus.checkup.models.Ailment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AilmentsListActivity extends BaseActivity {
    private DatabaseReference mAilmentReference;
    private ArrayList<Ailment> mAilments = new ArrayList<>();
    private AllAilmentsListAdapter mAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.noResultsImageView) ImageView mNoResultsImageView;
    @Bind(R.id.noResultsTextView) TextView mNoResultsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ailments_list);
        super.onCreateDrawer();
        ButterKnife.bind(this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mAilmentReference = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_AILMENTS)
                .child(uid);

        mAilmentReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot ailmentsSnapshot : snapshot.getChildren()) {
                    Ailment ailment = ailmentsSnapshot.getValue(Ailment.class);
                    mAilments.add(ailment);
                    Log.d("Ailment:", ailment + "");
                }

                if (mAilments.isEmpty()) {
                    mNoResultsImageView.setVisibility(View.VISIBLE);
                    mNoResultsTextView.setVisibility(View.VISIBLE);
                }

                setUpRecyclerAdapter();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setUpRecyclerAdapter() {
        mAdapter = new AllAilmentsListAdapter(getApplicationContext(), mAilments);
        mRecyclerView.setAdapter(mAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AilmentsListActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
