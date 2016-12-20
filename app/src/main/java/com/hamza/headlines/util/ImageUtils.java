package com.hamza.headlines.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Hamza Fetuga on 12/20/2016.
 */

public class ImageUtils {

        public static Observable<Bitmap> decodeBitmap(final Context context, final int resource) {
            return Observable.create(new Observable.OnSubscribe<Bitmap>() {
                @Override
                public void call(Subscriber<? super Bitmap> subscriber) {
                    Bitmap icon = BitmapFactory.decodeResource(context.getResources(), resource);
                    if (icon != null) {
                        subscriber.onNext(icon);
                    } else {
                        subscriber.onError(new NullPointerException());
                    }
                    subscriber.onCompleted();
                }
            });
        }

    }


