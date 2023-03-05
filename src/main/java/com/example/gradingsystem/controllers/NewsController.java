package com.example.gradingsystem.controllers;

import com.example.gradingsystem.entities.News;
import com.example.gradingsystem.services.news.NewsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news/")
public class NewsController {

    private NewsServiceImpl newsService;

    @Autowired
    public NewsController(NewsServiceImpl newsService) {
        this.newsService = newsService;
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PostMapping
    public News addNews(@RequestBody News news) {
        return newsService.saveNews(news);
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping
    public List<News> findAllNews() {
        return newsService.getNewsList();
    }

    //@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @GetMapping(path = "{id}/")
    public News findNewsByID(@PathVariable int id) {
        return newsService.getNews(id);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @PutMapping(path = "edit/{id}/")
    public News updateNews(@PathVariable int id, @RequestBody News news) {
        news.setId(id);
        return newsService.updateNews(news);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('TEACHER')")
    @DeleteMapping(path = "{id}/")
    public String deleteNews(@PathVariable int id) {
        return newsService.deleteNews(id);
    }
}
