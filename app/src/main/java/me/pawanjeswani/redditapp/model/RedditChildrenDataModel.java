package me.pawanjeswani.redditapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RedditChildrenDataModel {
    @SerializedName("subreddit")
    @Expose
    private String subreddit;

    @SerializedName("subreddit_id")
    @Expose
    private String subreddit_id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("num_comments")
    @Expose
    private int num_comments;

    @SerializedName("ups")
    @Expose
    private int ups;

    @SerializedName("preview")
    @Expose
    private RedditPreviewDataModel preview;

    @SerializedName("url")
    @Expose
    private String url;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getSubreddit_id() {
        return subreddit_id;
    }

    public void setSubreddit_id(String subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum_comments() {
        return num_comments;
    }

    public void setNum_comments(int num_comments) {
        this.num_comments = num_comments;
    }

    public int getUps() {
        return ups;
    }

    public void setUps(int ups) {
        this.ups = ups;
    }

    public RedditPreviewDataModel getPreview() {
        return preview;
    }

    public void setPreview(RedditPreviewDataModel preview) {
        this.preview = preview;
    }
}
