package com.craigke.letschat.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.craigke.letschat.R;
import com.craigke.letschat.models.Result;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListImageAdapter extends RecyclerView.Adapter<ListImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Result> mResult;

    public ListImageAdapter(Context context, List<Result> results) {
        mContext = context;
        mResult = results;
    }
    public class ImageViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.userNameView) TextView mUserTextView;
        @BindView(R.id.categoryTextView) TextView mImageDescription;
        @BindView(R.id.ratingTextView) TextView mRatingView;
        @BindView(R.id.display_image) ImageView mImageView;

        private Context context;

        public ImageViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
        }
        public void bindImageViewer(Result results){
            Picasso.get().load(results.getUrls().getRegular()).into(mImageView);
            mUserTextView.setText(results.getUser().getUsername());
            mImageDescription.setText(results.getDescription());
            mRatingView.setText("People:" + results.getUser().getTotalLikes()+ "/Likes");
        }
    }
    @Override
    public int getItemCount(){
        return mResult.size();
    }
    @Override
    public ListImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int ViewTypes){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_image, parent,false);
        ImageViewHolder viewHolder = new ImageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListImageAdapter.ImageViewHolder holder, int position) {
        holder.bindImageViewer(mResult.get(position));

    }
}

