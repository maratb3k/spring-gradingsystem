package com.example.gradingsystem.services.news;

import com.example.gradingsystem.entities.News;

import java.util.List;

public interface NewsService {
    News saveNews(News news);
    News getNews(int id);
    List<News> getNewsList();
    News updateNews(News news);
    String deleteNews(int id);
}
