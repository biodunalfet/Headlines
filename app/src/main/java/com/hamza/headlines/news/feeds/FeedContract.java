package com.hamza.headlines.news.feeds;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public interface FeedContract {

    interface View {

        void showProgressIndicator(boolean active);

        void showError(String message);

        void showArticles(FeedResponse response);

        void showArticle(ArticleSummary articleSummary);

    }

    interface UserActionsListener {

        void getArticles(String sortBy, String sourceKey);

        void openTab(ArticleSummary articleSummary);
    }

}
