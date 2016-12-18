package com.hamza.headlines.news.headlines;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hamza.headlines.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends Fragment implements ArticleContract.View {

    Context context;
    ArticlePresenter presenter;
    private ProgressDialog progressDialog;
    RecyclerView recyclerView;
    String sourceKey;
    String sortBy;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        presenter = new ArticlePresenter(context, this);
        Bundle bundle = getArguments();
        sourceKey = bundle.getString("SOURCE_KEY");
        sortBy = bundle.getString("SORT_BY");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getArticles(sortBy, sourceKey);
    }

    @Override
    public void showProgressIndicator(boolean active) {
        if (progressDialog == null){
            progressDialog = new ProgressDialog(context);
        }
        
        if (active){
            progressDialog.show();
        }
        else{
            progressDialog.cancel();
        }
    }

    @Override
    public void showError(String message) {
        if (message == null){
            message = "An error occurred. Try Again";
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showArticles(List<Article> articles) {

    }

    @Override
    public void showArticle(Article article) {

    }
}
