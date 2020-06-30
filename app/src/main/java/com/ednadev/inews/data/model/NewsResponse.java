package com.ednadev.inews.data.model;

import java.util.ArrayList;

public class NewsResponse {
    private String status;
    private Integer totalResults;
    private ArrayList<NewsModel> articles;

    public NewsResponse(String status, Integer totalResults, ArrayList<NewsModel> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    public String getStatus() {
        return status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public ArrayList<NewsModel> getArticles() {
        return articles;
    }
}
