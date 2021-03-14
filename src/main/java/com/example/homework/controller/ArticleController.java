package com.example.homework.controller;

import com.example.homework.customexception.ResourceNotFoundException;
import com.example.homework.model.Article;
import com.example.homework.model.QArticle;
import com.example.homework.model.Review;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.ReviewRepository;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    EntityManager entityManager;

    @GetMapping("/articles")
    public List<Article> getAllArticles(@RequestParam Map<String, String> allParams){
        JPAQuery<Article> query = new JPAQuery<Article>(entityManager);
        QArticle qArticle = QArticle.article;
        if (allParams.containsKey("title") && allParams.containsKey("starCount")){
            return query.select(qArticle).from(qArticle).where(qArticle.title.contains(allParams.get("title")).and(qArticle.starCount.eq(Integer.valueOf(allParams.get("starCount"))))).fetch();
        }
        List<Article> articles = query.select(qArticle).from(qArticle).fetch();
        return articles;
    }

    @GetMapping("/articles/{id}")
    public Article getArticle(@PathVariable Long id) throws ResourceNotFoundException{
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + id));
        return article;
    }

    @PostMapping("/articles")
    public void saveArticle(@RequestBody Article article){
        articleRepository.save(article);
    }

    @PutMapping("/articles/increase/{id}")
    public Boolean increaseStarCount(@PathVariable Long id) throws ResourceNotFoundException{
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + id));
        int newCount = article.getStarCount() + 1;
        article.setStarCount(newCount);
        articleRepository.save(article);
        return Boolean.TRUE;
    }

    @PutMapping("/articles/reduce/{id}")
    public Boolean reduceStarCount(@PathVariable Long id) throws ResourceNotFoundException{
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + id));
        int newCount = article.getStarCount() - 1;
        article.setStarCount(newCount);
        articleRepository.save(article);
        return Boolean.TRUE;
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) throws ResourceNotFoundException {
        Article article1 = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + id));
        if (article.getArticleContent() != null){
            article1.setArticleContent(article.getArticleContent());
        }
        if (article.getAuthor() != null){
            article1.setAuthor(article.getAuthor());
        }
        if (article.getPublishDate() != null){
            article1.setPublishDate(article.getPublishDate());
        }
        if (article.getTitle() != null){
            article1.setTitle(article.getTitle());
        }
        return articleRepository.save(article1);
    }

    @DeleteMapping("/articles/{id}")
    public Boolean deleteArticle(@PathVariable Long id) throws ResourceNotFoundException {
        Article article = articleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + id));
        articleRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
