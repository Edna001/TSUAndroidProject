package com.ednadev.inews.data.repository;

import com.ednadev.inews.data.model.NewsResponse;
import com.ednadev.inews.data.rest.NewsService;
import com.ednadev.inews.utils.NewsCategoryEnum;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class NewsRepository {

    private NewsService service;

    @Inject
    public NewsRepository(NewsService newsService) {
        service = newsService;
    }

    public Flowable<NewsResponse> getNews() {
        return service.getNews();
    }

    public Flowable<NewsResponse> getCategoryNews(String categoryEnum) { return service.getNewsByCategory(categoryEnum); }
}