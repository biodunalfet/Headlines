package com.hamza.headlines.news.feedSelect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.hamza.headlines.news.feeds.FeedFragment;
import com.hamza.headlines.util.Constants;
import com.hamza.headlines.util.Helpers;

/**
 * Created by Hamza Fetuga on 12/18/2016.
 */

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    String[] sortBys;
    int count;

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setSortBys(String[] sortBys){
        this.sortBys = sortBys;
        this.count = sortBys.length;
    }

    @Override
    public Fragment getItem(int position) {
        FeedFragment fragment = new FeedFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SORT_BY_KEY, sortBys[position]);
        bundle.putString(Constants.SOURCE_KEY, FeedSelectFragment.sourceKey);
        fragment.setArguments(bundle);
        return fragment;
    }

    public String getTitle(int position){
        return Helpers.toSentenceCase(sortBys[position]).concat(" News");
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(position);
    }
}
