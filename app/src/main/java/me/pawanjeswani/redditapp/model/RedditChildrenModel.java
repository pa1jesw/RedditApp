package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditChildrenModel {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("data")
    @Expose
    private RedditChildrenDataModel data;

    public RedditChildrenDataModel getData() {
        return data;
    }

    public void setData(RedditChildrenDataModel data) {
        this.data = data;
    }

    public String getKind() {

        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
