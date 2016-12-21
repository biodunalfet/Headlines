package com.hamza.headlines.news;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.support.customtabs.CustomTabsIntent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hamza.headlines.R;
import com.hamza.headlines.news.feeds.ArticleSummary;
import com.hamza.headlines.news.sources.SourceFragment;
import com.hamza.headlines.util.Constants;
import com.hamza.headlines.util.CustomTabActivityHelper;
import com.hamza.headlines.util.ImageUtils;
import com.hamza.headlines.util.WebViewFallback;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public class NewsActivity extends AppCompatActivity {

    private CustomTabActivityHelper mCustomTabActivityHelper;
    CompositeSubscription mSubscriptions;
    private Bitmap mActionButtonBitmap;
    private Bitmap mCloseButtonBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSubscriptions = new CompositeSubscription();
        setupCustomTabsHelper();
        FrameLayout container = (FrameLayout) findViewById(R.id.content_news);

        if (container != null) {
            SourceFragment fragment = new SourceFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.content_news,
                   fragment, null).commit();
        }


    }

    public void setActionBarTitle(String title){
        try{
            getSupportActionBar().setTitle(title);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setActionBarSubtitle(String actionBarSubtitle) {
        try{
            getSupportActionBar().setSubtitle(actionBarSubtitle);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setupCustomTabsHelper(){
        mCustomTabActivityHelper = new CustomTabActivityHelper();
        mCustomTabActivityHelper.setConnectionCallback(mConnectionCallback);
        decodeBitmap(R.drawable.ic_share_white_24dp);
        decodeBitmap(R.drawable.ic_close_white_24dp);
    }

    public void openCustomTab(ArticleSummary article){

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(getResources().getColor(R.color.colorPrimary));
        intentBuilder.setShowTitle(true);

        String shareMenuItemTitle = "Share"; // TODO: 12/20/2016 extract to strings.xml
        PendingIntent shareMenuItemPendingIntent = createPendingShareIntent(article);
        intentBuilder.addMenuItem(shareMenuItemTitle, shareMenuItemPendingIntent);
        // TODO: 12/20/2016 add bookmark (optional)

        intentBuilder.setCloseButtonIcon(mCloseButtonBitmap);

        intentBuilder.setActionButton(mActionButtonBitmap, "Share", createPendingShareIntent(article));

        // TODO: 12/20/2016 set Entry and exit animations for chrome tabs
        //intentBuilder.setStartAnimations(this, R.anim.)

        Uri articleUri = Uri.parse(article.getUrl());
        CustomTabActivityHelper.openCustomTab(this, intentBuilder.build(), articleUri, new WebViewFallback());

    }

    private void decodeBitmap(final int resource) {
        mSubscriptions.add(ImageUtils.decodeBitmap(this, resource)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Bitmap>() {
                    @Override
                    public void onCompleted() { }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(NewsActivity.this.getLocalClassName(), "There was a problem decoding the bitmap " + e);
                    }

                    @Override
                    public void onNext(Bitmap bitmap) {
                        if (resource == R.drawable.ic_share_white_24dp) {
                            mActionButtonBitmap = bitmap;
                        }
                        else if (resource == R.drawable.ic_close_white_24dp) {
                            mCloseButtonBitmap = bitmap;
                        }
                    }
                }));
    }



    private PendingIntent createPendingShareIntent(ArticleSummary article) {
        Intent actionIntent = new Intent(Intent.ACTION_SEND);
        actionIntent.setType("text/plain");
        actionIntent.putExtra(Intent.EXTRA_TEXT, String.format("%s %s %s",
                article.getTitle(),
                article.getUrl(),
                "via " + Constants.TWITTER_HANDLE));
        return PendingIntent.getActivity(getApplicationContext(), 0, actionIntent, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mCustomTabActivityHelper.bindCustomTabsService(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mCustomTabActivityHelper.unbindCustomTabsService(this);
    }

    private CustomTabActivityHelper.ConnectionCallback mConnectionCallback = new CustomTabActivityHelper.ConnectionCallback() {
        @Override
        public void onCustomTabsConnected() {
            //Toast.makeText(NewsActivity.this, "Service connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCustomTabsDisconnected() {
            //Toast.makeText(NewsActivity.this, "Service disconnected", Toast.LENGTH_SHORT).show();
        }
    };


}
