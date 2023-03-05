package com.example.gradingsystem.services.news;


import com.example.gradingsystem.entities.News;
import com.example.gradingsystem.repositories.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public News getNews(int id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    @Override
    public List<News> getNewsList() {
        return newsRepository.findAll();
    }

    @Override
    public News updateNews(News news) {
        return newsRepository.save(news);
    }

    @Override
    public String deleteNews(int id) {
        newsRepository.deleteById(id);
        return "News " + id + " deleted";
    }
}
