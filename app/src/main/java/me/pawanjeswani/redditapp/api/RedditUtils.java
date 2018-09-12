package me.pawanjeswani.redditapp.api;

public class RedditUtils {
    private RedditUtils() {
    }
    public static RedditService getResponseUser(){
        return RedditClient.getClient().create(RedditService.class);
    }
}
