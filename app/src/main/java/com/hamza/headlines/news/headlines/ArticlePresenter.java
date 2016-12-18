package com.hamza.headlines.news.headlines;

import android.content.Context;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class ArticlePresenter implements ArticleContract.UserActionsListener{

    Context context;
    ArticleContract.View mView;

    public ArticlePresenter(Context context, ArticleContract.View mView) {
        this.context = context;
        this.mView = mView;
    }

    @Override
    public void getArticles(String sortBy, String sourceKey) {

    }
}
