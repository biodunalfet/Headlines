package com.hamza.headlines.news.feedSelect;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hamza.headlines.R;
import com.hamza.headlines.news.NewsActivity;
import com.hamza.headlines.util.Constants;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedSelectFragment extends Fragment{

    Context context;
    private ProgressDialog progressDialog;
    static String sourceKey;
    String[] sortBys;
    private android.support.v4.view.ViewPager pager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        Bundle bundle = getArguments();
        sourceKey = bundle.getString(Constants.SOURCE_KEY);
        sortBys = bundle.getStringArray(Constants.SORT_BY_KEYS_LIST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_article, container, false);
        this.pager = (ViewPager) v.findViewById(R.id.pager);

        NewsPagerAdapter newsPagerAdapter = new NewsPagerAdapter(((NewsActivity) context).getSupportFragmentManager());
        if (sortBys == null){
            sortBys = new String[0];
        }

        newsPagerAdapter.setSortBys(sortBys);
        pager.setAdapter(newsPagerAdapter);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
