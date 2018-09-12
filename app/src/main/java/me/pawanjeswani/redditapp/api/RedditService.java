package me.pawanjeswani.redditapp.api;

import java.util.List;

import me.pawanjeswani.redditapp.model.RedditFeedModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RedditService {
    @Headers("Content-Type: application/json")
    @GET
    Call<RedditFeedModel> getFeeds(@Url String url);
}
