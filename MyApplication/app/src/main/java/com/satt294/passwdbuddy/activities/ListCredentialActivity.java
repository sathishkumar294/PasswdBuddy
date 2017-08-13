package com.satt294.passwdbuddy.activities;

import android.app.SearchManager;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;


import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.helpers.IMessageHelper;
import com.satt294.passwdbuddy.viewadaptors.RecyclerViewAdaptor;
import com.satt294.passwdbuddy.viewmodels.AddCredentialViewModel;
import com.satt294.passwdbuddy.viewmodels.ListCredentialViewModel;

import java.util.ArrayList;
import java.util.List;

public class ListCredentialActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    /**
     * Life Cycle Management
     */
    private LifecycleRegistry mRegistry = new LifecycleRegistry(this);


    private ListCredentialViewModel viewModel;
    private RecyclerViewAdaptor recyclerViewAdaptor;
    private RecyclerView recyclerView;

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
        setContentView(R.layout.activity_list_credential);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_list_credential);
        setSupportActionBar(toolbar);

        // The floating add icon to add new Credential
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the add credential activity
                Intent credentialIntent = new Intent(ListCredentialActivity.this, AddCredentialActivity.class);
                startActivity(credentialIntent);

            }
        });

        // Initialise services
        recyclerView = (RecyclerView) findViewById(R.id.rcRecyclerView);
        recyclerViewAdaptor = new RecyclerViewAdaptor(new ArrayList<Credential>(), new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //TODO: Open the credential view
                Credential credential = (Credential) view.getTag();
                Intent credentialIntent = new Intent(ListCredentialActivity.this, AddCredentialActivity.class);
                credentialIntent.putExtra("credId", credential.getCid());
                startActivity(credentialIntent);
                return;
            }
        }, new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Credential credential = (Credential) view.getTag();
                // Get the user confirmation and delete credential
                confirmAndDelete(credential);
                return true;
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerViewAdaptor);

        viewModel = ViewModelProviders.of(this).get(ListCredentialViewModel.class);

        viewModel.getCredentialsLiveList().observe(ListCredentialActivity.this, new Observer<List<Credential>>() {
            @Override
            public void onChanged(@Nullable List<Credential> credentialList) {
                recyclerViewAdaptor.addItems(credentialList);
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppDB.destropInstance();
    }

    /**
     * Implement the {@link IMessageHelper} interface to show the messages in the view
     *
     * @return
     */
    @NonNull
    protected IMessageHelper getMessageHelper() {
        return new IMessageHelper() {
            @Override
            public void showToast(String message) {
                Toast.makeText(ListCredentialActivity.this, message, Toast.LENGTH_LONG).show();
            }
        };
    }

    private void confirmAndDelete(final Credential credential) {
        AlertDialog deleteConfirmationDialog = new AlertDialog.Builder(this)
                .setTitle("Remove Credential")
                .setMessage("Are you sure to remove the credential permanently?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        viewModel.deleteCredential(credential, getMessageHelper());
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();

        deleteConfirmationDialog.show();

    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mRegistry;
    }
}
