package com.example.homework.model;

import javax.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long articleId;

    @Column
    private String reviewer;

    @Column
    private String reviewContent;

    @ManyToOne
    @JoinColumn(name = "article")
    private Article article;

    public Review() {
    }

    public Review(Long articleId, String reviewer, String reviewContent, Article article) {
        this.articleId = articleId;
        this.reviewer = reviewer;
        this.reviewContent = reviewContent;
        this.article = article;
    }

    public Long getId(){
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String review) {
        this.reviewer = reviewer;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
