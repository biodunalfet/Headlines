package com.hamza.headlines.news.feeds;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class FeedResponse {

    String status;
    String source;
    String sortBy;
    List<ArticleSummary> articles;

    public String getStatus() {
        return status;
    }

    public List<ArticleSummary> getArticleSummaries() {
        return articles;
    }
}
