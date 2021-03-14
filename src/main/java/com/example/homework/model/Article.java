package com.example.homework.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.implementation.bind.annotation.Default;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String articleContent;

    @Column
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date publishDate;

    @Column
    private int starCount;

    @Column
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Review> reviews;

    public Article() {
    }

    public Article(String title, String author, String articleContent, Date publishDate, int starCount, Set<Review> reviews) {
        this.title = title;
        this.author = author;
        this.articleContent = articleContent;
        this.publishDate = publishDate;
        this.starCount = starCount;
        this.reviews = reviews;
    }

    public Long getId(){
        return id;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

}
