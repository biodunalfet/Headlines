package com.hamza.headlines.news.sources;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hamza.headlines.R;
import com.hamza.headlines.news.NewsActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SourceFragment extends Fragment implements SourcesContract.View {

    Context context;
    SourcePresenter presenter;
    private ProgressDialog progressDialog;
    RecyclerView recyclerView;
    private RelativeLayout progressIndicator;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        presenter = new SourcePresenter(context, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_source, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        this.progressIndicator = (RelativeLayout) view.findViewById(R.id.progressIndicator);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getSources();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setTitle("Sources");
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void showProgressIndicator(boolean active) {
        if (progressIndicator == null) {
            this.progressIndicator = (RelativeLayout) view.findViewById(R.id.progressIndicator);
        }

        if (active) {
            progressIndicator.setVisibility(View.VISIBLE);
        } else {
            progressIndicator.setVisibility(View.GONE);
        }
    }

    @Override
    public void showSources(List<Source> sources) {

        if(sources == null){
            showError(null);
        }
        else{
            SourcesAdapter adapter = new SourcesAdapter();
            adapter.set(sources, context);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        }

    }

    @Override
    public void showError(String message) {
        
        //// TODO: 12/16/2016 use Snackbar 
        if (message == null){
            message = "An error occurred. Try again";
        }

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTitle(String title) {

        try {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onDestroy() {
        presenter.clearSubscription();
        super.onDestroy();
    }
}
