package com.example.homework.controller;

import com.example.homework.customexception.ResourceNotFoundException;
import com.example.homework.model.Article;
import com.example.homework.model.Review;
import com.example.homework.repository.ArticleRepository;
import com.example.homework.repository.ReviewRepository;
import com.fasterxml.jackson.core.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/reviews")
    public List<Review> getAllReviews(){
        return reviewRepository.findAll();
    }

    @GetMapping("/reviews/{id}")
    public Review getReview(@PathVariable Long id) throws ResourceNotFoundException {
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found on :: " + id));
        return review;
    }

    @PostMapping("/reviews/{articleId}")
    public void saveReview(@RequestBody Map<String, String> payload, @PathVariable Long articleId) throws ResourceNotFoundException{
        Review review = new Review();
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new ResourceNotFoundException("Article not found on :: " + articleId));
        review.setArticleId(articleId);
        review.setArticle(article);
        review.setReviewer("The Big Reviewer");
        review.setReviewContent(payload.get("reviewContent"));
        reviewRepository.save(review);
    }

    @PutMapping("/reviews/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Map<String, String> payload) throws ResourceNotFoundException{
        Review updateReview = reviewRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Review not found on :: " + id));
        updateReview.setReviewContent(payload.get("reviewContent"));
        return ResponseEntity.ok(updateReview);
    }

    @DeleteMapping("/reviews/{id}")
    public Boolean deleteReview(@PathVariable Long id){
        reviewRepository.deleteById(id);
        return Boolean.TRUE;
    }

}
