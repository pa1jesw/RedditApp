package me.pawanjeswani.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import me.pawanjeswani.redditapp.database.feedItemListEntity;
import me.pawanjeswani.redditapp.database.feedItemRepo;

public class feedItemViewModel extends ViewModel {

    private feedItemRepo feedItemRepo;

    public feedItemViewModel(feedItemRepo feedItemRepo) {
        this.feedItemRepo = feedItemRepo;
    }

    public LiveData<List<feedItemListEntity>>  getRepoFeeds(){
        return feedItemRepo.getAllFeed();
    }

    public  void insertEntity(feedItemListEntity entity){
        feedItemRepo.insertFeed(entity);
    }

    public void deleteAllFeed()
    {
        feedItemRepo.deleteAll();
    }

    public void deleteItem(feedItemListEntity entity)
    {
        feedItemRepo.deleteitem(entity);
    }

    public LiveData<feedItemListEntity> getListFeedVM(String id)
    {
        return feedItemRepo.getListofFeed(id);
    }
}
