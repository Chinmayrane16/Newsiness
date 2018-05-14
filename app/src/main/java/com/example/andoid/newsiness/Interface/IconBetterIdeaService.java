package com.example.andoid.newsiness.Interface;

import com.example.andoid.newsiness.Model.IconBetterIdea;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by Chinmay on 19-Apr-18.
 */

public interface IconBetterIdeaService {
    @GET
    Call<IconBetterIdea> getIconUrl (@Url String url);
}
