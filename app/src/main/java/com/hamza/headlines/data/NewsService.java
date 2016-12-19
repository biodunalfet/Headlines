package com.hamza.headlines.data;

import com.hamza.headlines.news.feeds.FeedResponse;
import com.hamza.headlines.news.sources.Source;
import com.hamza.headlines.news.sources.SourcesContract;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public interface NewsService {

    @GET("/v1/sources?language=en")
    Observable<SourceResponse> getSources();

    @GET("/v1/articles")
    Observable<FeedResponse> getFeedFromSourceWithSort(@Query("source") String source,
                                                       @Query("sortBy") String sortBy,
                                                       @Query("apiKey") String apiKey);

}
