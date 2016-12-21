package com.hamza.headlines.news.sources;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hamza.headlines.data.Repository;
import com.hamza.headlines.data.SourceResponse;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public class SourcePresenter implements SourcesContract.UserActionsListener {

    Context context;
    SourcesContract.View mView;
    Repository repository;
    CompositeSubscription subscriptions;

    public SourcePresenter(@NonNull Context context, SourcesContract.View mView) {
        this.context = context;
        this.mView = mView;
        this.repository = new Repository();

        if (this.subscriptions == null){
            subscriptions = new CompositeSubscription();
        }
    }

    @Override
    public void getSources() {
        mView.showProgressIndicator(true);

        Repository.DefaultFactory()
                .getSourcesObservable(new Repository.GetSourcesResponseCallback() {
                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.showProgressIndicator(false);
                        e.printStackTrace();
                    }

                    @Override
                    public void onSuccess(SourceResponse response) {

                        if (response != null && response.getStatus().equals("ok")){
                            mView.showProgressIndicator(false);
                            mView.showSources(response.getSourcesList());
                        }
                        else {
                            mView.showError(null);
                        }

                    }
                }, subscriptions);
    }

    @Override
    public void clearSubscription() {
        subscriptions.unsubscribe();
    }

    @Override
    public void setTitle(String message) {
        mView.showTitle(message);
    }

}
