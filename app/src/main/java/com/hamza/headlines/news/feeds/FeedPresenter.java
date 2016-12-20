package com.hamza.headlines.news.feeds;

import android.content.Context;

import com.hamza.headlines.data.Repository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class FeedPresenter implements FeedContract.UserActionsListener{

    private CompositeSubscription subscriptions;
    Context context;
    FeedContract.View mView;

    public FeedPresenter(Context context, FeedContract.View mView) {
        this.context = context;
        this.mView = mView;

        if (this.subscriptions == null){
            subscriptions = new CompositeSubscription();
        }
    }

    @Override
    public void getArticles(String sortBy, String sourceKey) {

        mView.showProgressIndicator(true);

        Repository.DefaultFactory().getFeedResponseObservable(new Repository.GetFeedSourcesCallback() {
            @Override
            public void onError(Throwable e) {
                mView.showProgressIndicator(false);
                mView.showError(e.getMessage());
            }

            @Override
            public void onSuccess(FeedResponse response) {
                mView.showProgressIndicator(false);
                mView.showArticles(response);
            }
        }, subscriptions, sourceKey, sortBy);

    }

    @Override
    public void openTab(ArticleSummary articleSummary) {
        mView.showArticle(articleSummary);
    }

    public void unSubscribe(){
        if (subscriptions != null){ subscriptions.unsubscribe();}
    }
}
