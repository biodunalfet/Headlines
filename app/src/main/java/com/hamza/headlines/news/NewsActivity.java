package com.hamza.headlines.news;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.hamza.headlines.R;
import com.hamza.headlines.news.sources.SourceFragment;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FrameLayout container = (FrameLayout) findViewById(R.id.content_news);

        if (container != null) {
            SourceFragment fragment = new SourceFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_news,
                   fragment ,null).commit();
        }

    }

}
