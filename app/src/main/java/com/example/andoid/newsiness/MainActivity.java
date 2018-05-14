package com.example.andoid.newsiness;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.andoid.newsiness.Adapter.ListSourceAdapter;
import com.example.andoid.newsiness.Common.Common;
import com.example.andoid.newsiness.Interface.NewsService;
import com.example.andoid.newsiness.Model.WebSite;
import com.google.gson.Gson;

import dmax.dialog.SpotsDialog;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView listWebsite;
    RecyclerView.LayoutManager layoutManager;
    NewsService mService;
    ListSourceAdapter adapter;
    SpotsDialog dialog;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // INIT CACHE
        Paper.init(this);

        // INIT SERVICE
        mService = Common.getNewsService();

        //Init View
        swipeRefreshLayout = findViewById(R.id.main_swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWebsiteSource(true);
            }
        });

        listWebsite = findViewById(R.id.main_recyclerview);
        listWebsite.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        listWebsite.setLayoutManager(layoutManager);


        dialog = new SpotsDialog(this);

        loadWebsiteSource(false);

    }

    private void loadWebsiteSource(boolean isRefreshed)
    {
        if(!isRefreshed)
        {
            String cache = Paper.book().read("cache");
            // if we have cache
            if(cache != null && !cache.isEmpty())
            {
                WebSite website = new Gson().fromJson(cache,  WebSite.class);  // CONVERT CACHE FROM JSON TO OBJECT
                adapter = new ListSourceAdapter(getBaseContext(),website);
                adapter.notifyDataSetChanged();
                listWebsite.setAdapter(adapter);
            }
            else  // IF NO CACHE
            {
                dialog.show();
                //Fetch new data
                mService.getSources().enqueue(new Callback<WebSite>() {
                    @Override
                    public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                        adapter = new ListSourceAdapter(getBaseContext(), response.body());
                        adapter.notifyDataSetChanged();
                        listWebsite.setAdapter(adapter);

                        // Write to PAPERBOOK
                        Paper.book().write("cache", new Gson().toJson(response.body()));

                    }

                    @Override
                    public void onFailure(Call<WebSite> call, Throwable t) {

                    }
                });
                dialog.dismiss();
            }
        }
        else  // If SWIPE to REFRESH
        {
            dialog.show();
            //Fetch new data
            mService.getSources().enqueue(new Callback<WebSite>() {
                @Override
                public void onResponse(Call<WebSite> call, Response<WebSite> response) {
                    adapter = new ListSourceAdapter(getBaseContext(), response.body());
                    adapter.notifyDataSetChanged();
                    listWebsite.setAdapter(adapter);

                    // Write to PAPERBOOK
                    Paper.book().write("cache", new Gson().toJson(response.body()));

                    // DISMISS REFRESH PROGRESSING
                    swipeRefreshLayout.setRefreshing(false);

                }

                @Override
                public void onFailure(Call<WebSite> call, Throwable t) {

                }
            });
            dialog.dismiss();
        }
    }


}
