package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditFeedModel {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private RedditDataModel redditDataModel;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditDataModel getRedditDataModel() {
        return redditDataModel;
    }

    public void setRedditDataModel(RedditDataModel redditDataModel) {
        this.redditDataModel = redditDataModel;
    }
}
