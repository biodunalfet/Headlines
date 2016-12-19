package com.hamza.headlines.news.feeds;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hamza.headlines.R;
import com.hamza.headlines.util.Constants;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment implements FeedContract.View {


    private ProgressDialog progressDialog;
    private Context context;
    private android.support.v7.widget.RecyclerView recyclerView;
    private String sortBy;
    private String source;
    private android.widget.RelativeLayout progressIndicator;
    View v;
    FeedPresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Bundle bundle = getArguments();
        sortBy = bundle.getString(Constants.SORT_BY_KEY);
        source = bundle.getString(Constants.SOURCE_KEY);
        presenter = new FeedPresenter(context, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_feed, container, false);
        this.progressIndicator = (RelativeLayout) v.findViewById(R.id.progressIndicator);
        this.recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getArticles(sortBy, source);
    }

    @Override
    public void showProgressIndicator(boolean active) {
        if (progressIndicator == null) {
            this.progressIndicator = (RelativeLayout) v.findViewById(R.id.progressIndicator);
        }

        if (active) {
            progressIndicator.setVisibility(View.VISIBLE);
        } else {
            progressIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String message) {
        if (message == null) {
            message = "An error occurred. Try Again";
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArticles(FeedResponse response) {

        if (response != null){
            FeedRecyclerAdapter adapter = new FeedRecyclerAdapter(context, response.getArticleSummaries());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
        else{
            showError(null);
        }

    }


    @Override
    public void showArticle(ArticleSummary articleSummary) {

    }

}
