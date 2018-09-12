package me.pawanjeswani.redditapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import me.pawanjeswani.redditapp.FullscreenActivity;
import me.pawanjeswani.redditapp.R;
import me.pawanjeswani.redditapp.model.RedditChildrenDataModel;
import me.pawanjeswani.redditapp.model.RedditChildrenModel;
import me.pawanjeswani.redditapp.model.RedditImage0model;
import me.pawanjeswani.redditapp.model.RedditImagesDataModel;
import me.pawanjeswani.redditapp.model.RedditPreviewDataModel;
import me.pawanjeswani.redditapp.model.RedditSourceDataModel;

public class FeedItemRVAdapter extends RecyclerView.Adapter<FeedItemRVAdapter.feed_holder> {

    private List<RedditChildrenModel> mData;
    RedditChildrenDataModel childrenModeel;
    RedditPreviewDataModel previewDataModel;
    ArrayList<RedditImagesDataModel> image0model;
    RedditImagesDataModel imagesDataModel;
    RedditSourceDataModel sourceDataModel;
    private Activity mActivity;
    private int mDist;

    public FeedItemRVAdapter(List<RedditChildrenModel> mData, Activity mActivity, int mDist) {
        this.mData = mData;
        this.mActivity = mActivity;
        this.mDist = mDist;
    }

    @NonNull
    @Override
    public FeedItemRVAdapter.feed_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_feed_item,parent,
                false);
        return new feed_holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedItemRVAdapter.feed_holder holder, int position) {
            final RedditChildrenModel childrenModel = mData.get(position);
            holder.tv_rec_upvotes.setText(""+childrenModel.getData().getUps());
            holder.tv_rec_comments.setText(""+childrenModel.getData().getNum_comments());
            holder.tv_rec_title.setText(""+childrenModel.getData().getTitle());
            String uri = "";
            previewDataModel = childrenModel.getData().getPreview();
            if(previewDataModel !=null)
            {
            imagesDataModel = previewDataModel.getImageat(0);
            sourceDataModel = imagesDataModel.getRedditSourceDataModels();
            uri = sourceDataModel.getUrl();
            Log.d("uri for imgs",uri);
                Picasso.with(mActivity).load(uri).error(R.drawable.accden)
                        .placeholder(R.drawable.placeholder)
                        .into(holder.iv_rec_picture);
            }

        final String finalUri = uri;
        holder.iv_rec_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent n = new Intent(mActivity, FullscreenActivity.class);
               n.putExtra("uri", finalUri);
               mActivity.startActivity(n);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class feed_holder extends RecyclerView.ViewHolder {

        ImageView iv_rec_picture;
        TextView tv_rec_title;
        TextView tv_rec_upvotes;
        TextView tv_rec_comments;

        public feed_holder(View itemView) {
            super(itemView);
            iv_rec_picture = itemView.findViewById(R.id.ivFeedImage);
            tv_rec_title = itemView.findViewById(R.id.tvFeedTitle);
            tv_rec_comments = itemView.findViewById(R.id.tvFeedCommentCount);
            tv_rec_upvotes = itemView.findViewById(R.id.tvFeedUpvotes);
        }
    }
}
