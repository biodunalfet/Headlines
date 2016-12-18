package com.hamza.headlines.news.sources;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public class Source {

    public Source(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    String name;

    public ImageUrl getUrlsToLogos() {
        return urlsToLogos;
    }

    ImageUrl urlsToLogos;

    String id;

    String description;

    String url;

    String language;

    public String getCategory() {
        return category;
    }

    String category;

    String country;

    public class ImageUrl {

        public String small;
        public String medium;
        public String large;
    }

    public String[] sortBysAvailable;

}
