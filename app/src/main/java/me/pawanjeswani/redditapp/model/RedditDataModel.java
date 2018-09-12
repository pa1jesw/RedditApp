package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditDataModel {

    @SerializedName("modhash")
    @Expose
    private String modehash;

    @SerializedName("dist")
    @Expose
    private int dist;

    @SerializedName("children")
    @Expose
    private ArrayList<RedditChildrenModel> children;

    public String getModehash() {
        return modehash;
    }

    public void setModehash(String modehash) {
        this.modehash = modehash;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }

    public ArrayList<RedditChildrenModel> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RedditChildrenModel> children) {
        this.children = children;
    }
}
