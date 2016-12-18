package com.hamza.headlines.util;

/**
 * Created by Hamza Fetuga on 12/16/2016.
 */

public class Helpers {

    public static String toSentenceCase(String s){

        StringBuilder formed = new StringBuilder();

        String caps = s.substring(0, 1).toUpperCase();
        formed.append(caps).append(s.substring(1, s.length()));
        return formed.toString();
    }

}
