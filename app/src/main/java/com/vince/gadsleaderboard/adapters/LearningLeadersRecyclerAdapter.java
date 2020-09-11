package com.vince.gadsleaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.vince.gadsleaderboard.R;
import com.vince.gadsleaderboard.models.LearningLeaders;

import java.util.ArrayList;

public class LearningLeadersRecyclerAdapter extends RecyclerView.Adapter<LearningLeadersRecyclerAdapter.ViewHolder> {

    private final ArrayList<LearningLeaders> mLearningData;
    private final Context mContext;
    private final LayoutInflater mLayoutInflater;

    public LearningLeadersRecyclerAdapter(Context context, ArrayList<LearningLeaders> learningData) {
        mLearningData = learningData;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LearningLeaders learningLeaders = mLearningData.get(position);
        holder.mLearnerName.setText(learningLeaders.getName());
        holder.mLearnerDetails.setText(learningLeaders.getHours() + mContext.getString(R.string.learning_hours_string) + learningLeaders.getCountry());
        holder.mCurrentPosition = position;

        // Load images from API into imageView with glide
        Glide.with(mContext)
                .load(learningLeaders.getBadgeUrl())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.image_placeholder)
                .into(holder.mLearnerBadge);
    }

    @Override
    public int getItemCount() {
        return mLearningData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // Implement your custom views here
        public final TextView mLearnerName;
        public final TextView mLearnerDetails;
        public final ImageView mLearnerBadge;
        public int mCurrentPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            mLearnerName = itemView.findViewById(R.id.tv_name);
            mLearnerDetails = itemView.findViewById(R.id.tv_learning_details);
            mLearnerBadge = itemView.findViewById(R.id.imageView_badge);
        }

    }
}
