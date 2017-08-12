package com.satt294.passwdbuddy.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.entities.manager.CredentialManager;

import java.util.ArrayList;
import java.util.List;

public class CredListActivity extends AppCompatActivity {


    private CredentialManager credentialManager;

    private ListView mListView;

    /**
     * Override function to add search view to the app bar
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Add search to the app bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        // Associate the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credlist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // The floating add icon to add new Credential
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the add credential activity
                Intent credentialIntent = new Intent(CredListActivity.this, CredentialActivity.class);
                startActivity(credentialIntent);

            }
        });

        // Initialise services
        initServices();

        // Adaptor
        List<Credential> credentials = credentialManager.getAll();
        List<String> stringCreds = new ArrayList<>();
        for (Credential cred : credentials) {
            stringCreds.add(cred.getLogin() + "<br/>" + cred.getDescription());
        }
        ArrayAdapter<String> arrayAdaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringCreds);

        mListView = (ListView) findViewById(R.id.lv_ListView);
        mListView.setAdapter(arrayAdaptor);

    }

    /**
     * Initialise the member objects
     */
    private void initServices() {
        credentialManager = CredentialManager.getInstance(this);
    }

}
