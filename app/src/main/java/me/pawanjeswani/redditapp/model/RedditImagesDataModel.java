package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditImagesDataModel {

    @SerializedName("source")
    @Expose
    private RedditSourceDataModel redditSourceDataModels;

    public RedditSourceDataModel getRedditSourceDataModels() {
        return redditSourceDataModels;
    }

    public void setRedditSourceDataModels(RedditSourceDataModel redditSourceDataModels) {
        this.redditSourceDataModels = redditSourceDataModels;
    }
}
