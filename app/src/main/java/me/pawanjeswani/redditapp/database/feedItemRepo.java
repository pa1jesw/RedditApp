package me.pawanjeswani.redditapp.database;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class feedItemRepo {

    private final feedItemListDAO listDAO;

    public feedItemRepo(feedItemListDAO listDAO) {
        this.listDAO = listDAO;
    }

    public LiveData<feedItemListEntity> getListofFeed(String id){
        return listDAO.getFeedItemById(id);
    }

    public LiveData<List<feedItemListEntity>> getAllFeed(){

        return listDAO.getItems();
    }

    public void deleteitem(feedItemListEntity listEntity){
        new DeleteFeedItemAstync(listDAO).execute(listEntity);
    }

    public void deleteAll(){
        new DeleteallFeedItemAstync(listDAO).execute();
        listDAO.deleteeverything();
    }


    public void insertFeed(feedItemListEntity entity)
    {
        new InsertFeedItemAstync(listDAO).execute(entity);
    }


    //Async Tasks for deleting, inserting and deleteall

    private static class DeleteFeedItemAstync extends AsyncTask<feedItemListEntity,Void,Void> {

        private feedItemListDAO dao;

        public DeleteFeedItemAstync(feedItemListDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(feedItemListEntity... feedItemListEntities) {
            dao.deleteFeedItem(feedItemListEntities[0]);
            return null;
        }
    }

    private static class InsertFeedItemAstync extends AsyncTask<feedItemListEntity,Void,Void> {
        private feedItemListDAO dao;


        public InsertFeedItemAstync(feedItemListDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(feedItemListEntity... feedItemListEntities) {
            dao.insertFeedItemList(feedItemListEntities[0]);
            return null;
        }
    }

    private static class DeleteallFeedItemAstync extends AsyncTask<Void,Void,Void> {
        private feedItemListDAO dao;

        public DeleteallFeedItemAstync(feedItemListDAO dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteeverything();
            return null;
        }
    }

}

