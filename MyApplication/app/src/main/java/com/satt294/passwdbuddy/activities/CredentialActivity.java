package com.satt294.passwdbuddy.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.entities.entity.CredentialBuilder;
import com.satt294.passwdbuddy.entities.manager.CredentialManager;

public class CredentialActivity extends AppCompatActivity {

    private CredentialManager credentialManager;

    /**
     * The credential corresponding to this view
     */
    private Credential credential = null;

    private EditText mLoginEditText;
    private EditText mDescriptionEditText;
    private Button mSaveUpdateBtn;
    private Button mDeleteBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialise
        credentialManager = CredentialManager.getInstance(this);

        // Get the view components
        mSaveUpdateBtn = (Button) findViewById(R.id.btn_UpdateCredential);
        mDeleteBtn = (Button) findViewById(R.id.btn_DeleteCredential);
        mLoginEditText = (EditText) findViewById(R.id.et_Cred_Login);
        mDescriptionEditText = (EditText) findViewById(R.id.et_Cred_Description);

        // Hide the delete button, if the view is not associated to a credential yet
        if (credential == null || credential.getCid() == null) {
            mDeleteBtn.setVisibility(View.INVISIBLE);
        }

        mSaveUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the login and description
                String login = mLoginEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();

                if (login == null || login.isEmpty()) {
                    // TODO: P1: Ask the user to fix login
                } else if (description == null || description.isEmpty()) {
                    // TODO: P1: Ask the user to fix description
                }

                // Get the credential to store
                CredentialBuilder builder = new CredentialBuilder();
                Credential cred = builder.setLogin(login).setDescription(description).build();

                // Save the credentials
                credentialManager.saveOrUpdate(cred);
            }
        });

    }

}
