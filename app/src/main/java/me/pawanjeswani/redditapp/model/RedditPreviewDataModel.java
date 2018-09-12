package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RedditPreviewDataModel {

    @SerializedName("images")
    @Expose
    private ArrayList<RedditImagesDataModel> images;

    public ArrayList<RedditImagesDataModel> getImages() {
        return images;
    }

    public RedditImagesDataModel getImageat(int index) {
        return images.get(index);
    }

    public void setImages(ArrayList<RedditImagesDataModel> images) {
        this.images = images;
    }
}
