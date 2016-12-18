package com.hamza.headlines.data;

import com.hamza.headlines.news.sources.Source;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public class SourceResponse {

    String status;
    List<Source> sources;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Source> getSourcesList() {
        return sources;
    }

    public void setSources(List<Source> sources) {
        this.sources = sources;
    }
}
