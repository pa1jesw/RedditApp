package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditImage0model {

    @SerializedName("source")
    @Expose
    private RedditSourceDataModel source;

    public RedditSourceDataModel getSource() {
        return source;
    }

    public void setSource(RedditSourceDataModel source) {
        this.source = source;
    }
}
