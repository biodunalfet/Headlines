package com.hamza.headlines.data;

import android.content.Context;

import com.hamza.headlines.news.sources.Source;
import com.hamza.headlines.news.sources.SourcePresenter;
import com.hamza.headlines.news.sources.SourcesContract;
import com.hamza.headlines.util.Strings;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public class Repository {

    Observable<List<Source>> srcObs;
    RxJavaCallAdapterFactory rxAdapter;
    private Retrofit retrofit;
    private NewsService newsService;

    public static Repository DefaultFactory() {
        return new Repository();
    }

    private void createApiService() {

        if (retrofit == null) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
            okHttpClientBuilder.addInterceptor(interceptor);

            retrofit = new Retrofit.Builder().baseUrl(Strings.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClientBuilder.build()).build();
        }

        if (newsService == null){
            newsService = retrofit.create(NewsService.class);
        }
    }

    public void getSourcesObservable(final GetSourcesResponseCallback callback, CompositeSubscription subscriptions){
        createApiService();
        Observable<SourceResponse> sourceObs = newsService.getSources().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        Subscription subscription = sourceObs.subscribe(new Subscriber<SourceResponse>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(SourceResponse response) {
                        callback.onSuccess(response);
                    }
                });

        subscriptions.add(subscription);
    }

    public interface GetSourcesResponseCallback {

        void onError(Throwable e);

        void onSuccess(SourceResponse response);

    }


}
