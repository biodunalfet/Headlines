package com.hamza.headlines.util;

import android.graphics.drawable.Drawable;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class Helpers {

    public static String toSentenceCase(String s){

        try {
            StringBuilder formed = new StringBuilder();

            String caps = s.substring(0, 1).toUpperCase();
            formed.append(caps).append(s.substring(1, s.length()));
            return formed.toString();
        }catch (StringIndexOutOfBoundsException e){
            e.printStackTrace();
            return s;
        }
    }

    public static String FormatAuthorName(String s){

        if (s != null) {
            String sLower = s.toLowerCase();
            String[] stringTokens = sLower.split("\\s");
            StringBuilder formatted = new StringBuilder();

            for (String str : stringTokens) {
                formatted.append(toSentenceCase(str)).append(" ");
            }

            return formatted.toString();
        }
        else{
            return "";
        }

    }

}
