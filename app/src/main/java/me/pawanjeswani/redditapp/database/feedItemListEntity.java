package me.pawanjeswani.redditapp.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import me.pawanjeswani.redditapp.model.RedditFeedModel;

@Entity (tableName = "feedstable")
@TypeConverters(Converters.class)
public class feedItemListEntity  {

    @PrimaryKey
    @NonNull
    public String id;

    //@ColumnInfo (name = "redditFeed")
    public RedditFeedModel redditFeedModel;

    public feedItemListEntity(String id, RedditFeedModel redditFeedModel) {
        this.id = id;
        this.redditFeedModel = redditFeedModel;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public RedditFeedModel getRedditFeedModel() {
        return redditFeedModel;
    }
}
