package me.pawanjeswani.redditapp.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import me.pawanjeswani.redditapp.model.RedditFeedModel;

@Database(entities = {feedItemListEntity.class},version = 1)
@TypeConverters({Converters.class})


public abstract class feedItemListDB extends RoomDatabase {

    private static final String DB_NAME = "feedDatabase.db";
    private static volatile feedItemListDB instance;

    public static synchronized feedItemListDB getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }

    private static feedItemListDB create(Context context) {
        return Room.databaseBuilder(context,
                feedItemListDB.class,
                DB_NAME).fallbackToDestructiveMigration()
                .build();
    }

    public abstract feedItemListDAO feedItemListDAO();


}
class Converters {
    @TypeConverter
    public static RedditFeedModel fromString(String value) {
        Type listType = new TypeToken<RedditFeedModel>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromRedditFeedModel(RedditFeedModel list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    }
