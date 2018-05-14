package com.example.andoid.newsiness.Interface;

import android.provider.ContactsContract;

import com.example.andoid.newsiness.Model.WebSite;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Chinmay on 18-Apr-18.
 */

public interface NewsService {
    @GET("v1/sources?language=en")
    Call<WebSite> getSources();
}
