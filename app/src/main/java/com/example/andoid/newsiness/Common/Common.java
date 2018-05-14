package com.example.andoid.newsiness.Common;

import com.example.andoid.newsiness.Interface.IconBetterIdeaService;
import com.example.andoid.newsiness.Interface.NewsService;
import com.example.andoid.newsiness.Model.Icon;
import com.example.andoid.newsiness.Model.IconBetterIdea;
import com.example.andoid.newsiness.Remote.IconBetterIdeaClient;
import com.example.andoid.newsiness.Remote.RetrofitClient;

import retrofit2.Retrofit;

/**
 * Created by Chinmay on 19-Apr-18.
 */

public class Common {
    private static final String BASE_URL = "https://newsapi.org/";

    private static final String API_KEY = "ed4335448bab455dbbef1ccac7dac3fd";

    public static NewsService getNewsService()
    {
        return RetrofitClient.getClient(BASE_URL).create(NewsService.class);
    }

    public static IconBetterIdeaService getIconService()
    {
        return IconBetterIdeaClient.getClient().create(IconBetterIdeaService.class);
    }


}
