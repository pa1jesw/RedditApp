package me.pawanjeswani.redditapp.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface feedItemListDAO {

    @Query("SELECT * FROM feedstable")
    LiveData<List<feedItemListEntity>>  getItems();

    @Insert (onConflict = REPLACE)
    void insertFeedItemList (feedItemListEntity feedItemListEntity);

    @Query("SELECT * FROM feedstable Where id   = :id")
    LiveData<feedItemListEntity> getFeedItemById(String id);

    @Query("DELETE FROM feedstable")
    void deleteeverything();

    @Delete
    int deleteFeedItem(feedItemListEntity feedItemListEntity);


}
