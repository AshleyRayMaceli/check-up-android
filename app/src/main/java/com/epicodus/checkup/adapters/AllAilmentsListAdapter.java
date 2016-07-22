package com.epicodus.checkup.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epicodus.checkup.R;
import com.epicodus.checkup.models.Ailment;
import com.epicodus.checkup.ui.AilmentDetailActivity;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AllAilmentsListAdapter extends RecyclerView.Adapter<AllAilmentsListAdapter.AllAilmentsViewHolder> {
    private ArrayList<Ailment> mAllAilments = new ArrayList<>();
    private Context mContext;

    public AllAilmentsListAdapter(Context context, ArrayList<Ailment> allAilments) {
        mContext = context;
        mAllAilments = allAilments;
    }

    @Override
    public AllAilmentsListAdapter.AllAilmentsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ailment_list_item, parent, false);
        AllAilmentsViewHolder viewHolder = new AllAilmentsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AllAilmentsListAdapter.AllAilmentsViewHolder holder, int position) {
        holder.bindAllAilments(mAllAilments.get(position));
    }

    @Override
    public int getItemCount() {
        return mAllAilments.size();
    }

    public class AllAilmentsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.ailmentTitleTextView) TextView mAilmentTitleTextView;

        private Context mContext;

        public AllAilmentsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, AilmentDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("ailments", Parcels.wrap(mAllAilments));
            mContext.startActivity(intent);
        }

        public void bindAllAilments(Ailment ailment) {
            mAilmentTitleTextView.setText(ailment.getTitle());
        }
    }
}
