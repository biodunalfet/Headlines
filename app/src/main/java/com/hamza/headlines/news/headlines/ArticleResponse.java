package com.hamza.headlines.news.headlines;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class ArticleResponse {

    String status;
    String source;
    String sortBy;
    List<Article> articles;

    public String getStatus() {
        return status;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
