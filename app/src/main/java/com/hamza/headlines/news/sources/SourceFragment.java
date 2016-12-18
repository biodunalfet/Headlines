package com.hamza.headlines.news.sources;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
public class SourceFragment extends Fragment implements SourcesContract.View {

    Context context;
    SourcePresenter presenter;
    private ProgressDialog progressDialog;
    RecyclerView recyclerView;

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
        View view = inflater.inflate(R.layout.fragment_source, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.getSources();
    }

    @Override
    public void showProgressIndicator(boolean active) {

        //// TODO: 12/16/2016 use custom progress indicator
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
    public void onDestroy() {
        presenter.clearSubscription();
        super.onDestroy();
    }
}
