package com.delorenzo.utils;

import com.delorenzo.persistence.entity.Article;
import java.util.ArrayList;
import java.util.List;

public class RowArticle {
    private final List<Article> articles;

    public RowArticle( ) {
        this.articles = new ArrayList<Article>();
    }
    public List<Article> getArticles(){ 
        return this.articles;
    }

    public RowArticle(List<Article> articleList) { 
        this.articles = articleList; 
    }
 
 
}
