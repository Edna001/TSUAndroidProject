package com.ednadev.inews.data.rest;

import com.ednadev.inews.data.model.NewsResponse;
import com.ednadev.inews.utils.NewsCategoryEnum;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @GET("top-headlines?country=us&apiKey=faf639556a6c4811b7dfcf6f3807bed7")
    Flowable<NewsResponse> getNews();

    @GET("top-headlines?country=us&apiKey=faf639556a6c4811b7dfcf6f3807bed7")
    Flowable<NewsResponse> getNewsByCategory(@Query("category") String categoryEnum);
}
