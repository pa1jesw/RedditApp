package me.pawanjeswani.redditapp;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import me.pawanjeswani.ViewModel.feedItemViewModel;
import me.pawanjeswani.redditapp.adapter.FeedItemRVAdapter;
import me.pawanjeswani.redditapp.api.RedditUtils;
import me.pawanjeswani.redditapp.api.networkConnection;
import me.pawanjeswani.redditapp.database.feedItemListDAO;
import me.pawanjeswani.redditapp.database.feedItemListDAO_Impl;
import me.pawanjeswani.redditapp.database.feedItemListDB;
import me.pawanjeswani.redditapp.database.feedItemListEntity;
import me.pawanjeswani.redditapp.database.feedItemRepo;
import me.pawanjeswani.redditapp.model.RedditChildrenModel;
import me.pawanjeswani.redditapp.model.RedditDataModel;
import me.pawanjeswani.redditapp.model.RedditFeedModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RedditDataModel redditDataModel;
    private RedditFeedModel redditFeedModel;
    private ArrayList<RedditChildrenModel> redditChildrenModelArrayList;
    private RecyclerView rvFeed;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FeedItemRVAdapter rvAdapter ;
    private int mDash;
    @NonNull
    private String typeurl = "/r/cats.json";
    private feedItemViewModel listViewModel;
    private feedItemListDB listDB;
    private feedItemRepo listRepo;
    private feedItemListDAO listDAO;
    private feedItemListEntity entity;
    private feedItemViewModel viewModel;
    private Boolean dumBool= false;
    private String[] allFeedAr = {
            "/r/adviceanimals.json","/r/all.json",
            "/r/alternativeart.json","/r/aww.json",
            "/r/cats.json","/r/gifs.json","/r/images.json",
            "/r/photoshopbattles.json","/r/pics.json"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //binding java obj with xmls
        rvFeed = findViewById(R.id.rvFeedItems);
        swipeRefreshLayout = findViewById(R.id.swrl);

        //iniat 1st time

        //initializing Room DB
        initroom();
        if(networkConnection.IsNetworkConnected(getApplicationContext()))
            dumBool = initallfeeds();
        else
            toast(getApplicationContext(),"Please Enable Network Connection");
        if(!dumBool)
            toast(this,"Wait Sometime for Initial Loading");

        //initializing RV
        initrv();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(networkConnection.IsNetworkConnected(getApplicationContext()))
                    fetchAndSaveFeed(typeurl);
                else
                    toast(getApplicationContext(),"Please Check Your internet connection");
                swipeRefreshLayout.setRefreshing(false);
            }
        });


    }

    private void toast(Context applicationContext, String msg) {
        Toast.makeText(applicationContext, ""+msg, Toast.LENGTH_LONG).show();
    }

    private void initroom() {
        listDB = feedItemListDB.getInstance(getApplicationContext());
        listRepo = new feedItemRepo(new feedItemListDAO_Impl(listDB));
        viewModel = new feedItemViewModel(listRepo);
    }

    private void initrv() {
        rvFeed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvFeed.hasFixedSize();
    }

    private void offlineRoomLoading(final String typeurl)
    {
        viewModel.getListFeedVM(typeurl).observe(this, new Observer<feedItemListEntity>() {
            @Override
            public void onChanged(@Nullable feedItemListEntity entity) {
                redditFeedModel = entity.getRedditFeedModel();
                if(redditFeedModel !=null) {
                    redditDataModel = redditFeedModel.getRedditDataModel();
                    if(redditDataModel !=null)
                    {
                        redditChildrenModelArrayList = redditDataModel.getChildren();
                        setAdapterRV(redditChildrenModelArrayList);
                    }
                    else
                        toast(getApplicationContext(),"please try again");
                }
                else
                    toast(getApplicationContext(),"please try again");
            }
        });

    }

    private Boolean initallfeeds(){
        int i = 0;
        for(i =0;i<9;i++)
        {
            RedditFeedModel dumModel = callapi(allFeedAr[i]);
            feedItemListEntity dumentity = new feedItemListEntity(allFeedAr[i], dumModel);
            viewModel.insertEntity(dumentity);
        }
        if (i==9)
            return true;
        else
            return false;
    }


    private void setAdapterRV(ArrayList<RedditChildrenModel> redditChildrenModelArrayList) {
        rvAdapter = new FeedItemRVAdapter(redditChildrenModelArrayList,this,mDash);
        rvFeed.setAdapter(rvAdapter);
        rvFeed.getAdapter().notifyDataSetChanged();
    }

    private void fetchAndSaveFeed(String typeurl){
        //calling server for data
        redditFeedModel = callapi(typeurl);
        //creating db entity
        entity = new feedItemListEntity(typeurl,redditFeedModel);
        //inserting that entity
        viewModel.insertEntity(entity);
        //after saving entity calling it via offline room db
        offlineRoomLoading(typeurl);
        //setAdapterRV(redditChildrenModelArrayList);
    }

    private RedditFeedModel callapi(final String typeurl) {
        Call<RedditFeedModel> listCall =
                RedditUtils.getResponseUser().getFeeds(typeurl);
        listCall.enqueue(new Callback<RedditFeedModel>() {
            @Override
            public void onResponse(Call<RedditFeedModel> call, Response<RedditFeedModel> response) {
                Log.d("onresponse",response.body().getKind());
//                Toast.makeText(Main.this, "called server for "+typeurl, Toast.LENGTH_LONG).show();
                redditFeedModel = response.body();
            }

            @Override
            public void onFailure(Call<RedditFeedModel> call, Throwable t) {
                Log.d("onfailure",t.getMessage().toString()+t.getLocalizedMessage());
                Toast.makeText(Main.this, "Server not responding", Toast.LENGTH_LONG).show();
            }
        });

        return redditFeedModel;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        int id = item.getItemId();
        if(!networkConnection.IsNetworkConnected(getApplicationContext())) {
            toast(getApplicationContext(), "Please Check your Internet");
            if (id == R.id.nav_adviceanimals) {
                typeurl = "/r/adviceanimals.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_all) {
                typeurl = "/r/all.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_alternativeart) {
                typeurl = "/r/alternativeart.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_aww) {
                typeurl = "/r/aww.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_cats) {
                typeurl = "/r/cats.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_gifs) {
                typeurl = "/r/gifs.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_images) {
                typeurl = "/r/images.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_photoshopbattles) {
                typeurl = "/r/photoshopbattles.json";
                offlineRoomLoading(typeurl);
            } else if (id == R.id.nav_pics) {
                typeurl = "/r/pics.json";
                offlineRoomLoading(typeurl);
            }
        }
            else {
            if (id == R.id.nav_adviceanimals) {
                typeurl = "/r/adviceanimals.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_all) {
                typeurl = "/r/all.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_alternativeart) {
                typeurl = "/r/alternativeart.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_aww) {
                typeurl = "/r/aww.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_cats) {
                typeurl = "/r/cats.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_gifs) {
                typeurl = "/r/gifs.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_images) {
                typeurl = "/r/images.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_photoshopbattles) {
                typeurl = "/r/photoshopbattles.json";
                fetchAndSaveFeed(typeurl);
            } else if (id == R.id.nav_pics) {
                typeurl = "/r/pics.json";
                fetchAndSaveFeed(typeurl);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
