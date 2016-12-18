package com.hamza.headlines.news.headlines;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public interface ArticleContract {

    interface View {

        void showProgressIndicator(boolean active);

        void showError(String message);

        void showArticles(List<Article> articles);

        void showArticle(Article article);
    }

    interface UserActionsListener {

        void getArticles(String sortBy, String sourceKey);

    }

}
