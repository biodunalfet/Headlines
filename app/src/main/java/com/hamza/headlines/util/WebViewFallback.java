package com.hamza.headlines.util;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.hamza.headlines.news.webview.WebViewActivity;

/**
 * Created by Hamza Fetuga on 12/20/2016.
 */

public class WebViewFallback implements CustomTabActivityHelper.CustomTabFallback {


    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(Constants.ARTICLE_URL, uri.toString());
        activity.startActivity(intent);
    }
}
