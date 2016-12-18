package com.hamza.headlines.news.sources;

import java.util.List;

/**
 * Created by Hamza Fetuga on 12/15/2016.
 */

public interface SourcesContract {

    interface View {

        void showProgressIndicator(boolean active);

        void showSources(List<Source> sources);

        void showError(String message);

    }

    interface UserActionsListener {

        void getSources();

        void clearSubscription();

    }

}
