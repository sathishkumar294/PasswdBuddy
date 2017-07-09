package com.satt294.passwdbuddy.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

import com.satt294.passwdbuddy.R;

/**
 * Activity to search and filter
 */
public class SearchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Only handle the search action
        handleIntent(getIntent());
    }

    /**
     * Function to handle only the search action
     *
     * @param intent
     */
    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // Get the query to search
            String query = intent.getStringExtra(SearchManager.QUERY);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }
}
