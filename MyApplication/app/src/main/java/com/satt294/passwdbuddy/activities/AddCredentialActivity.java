package com.satt294.passwdbuddy.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.satt294.passwdbuddy.R;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.entities.entity.CredentialBuilder;
import com.satt294.passwdbuddy.entities.manager.CredentialManager;
import com.satt294.passwdbuddy.helpers.ICredentialFormFiller;
import com.satt294.passwdbuddy.helpers.IMessageHelper;
import com.satt294.passwdbuddy.utils.values.FormStatus;
import com.satt294.passwdbuddy.viewmodels.AddCredentialViewModel;

public class AddCredentialActivity extends AppCompatActivity {

    private EditText mLoginEditText;
    private EditText mDescriptionEditText;
    private Button mSaveUpdateBtn;
    private FormStatus formStatus;
    private Integer updateCredId;


    /**
     * View Model
     */
    private AddCredentialViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_credential);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_add_credential);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Get the view components
        mSaveUpdateBtn = (Button) findViewById(R.id.btn_UpdateCredential);
        mLoginEditText = (EditText) findViewById(R.id.et_Cred_Login);
        mDescriptionEditText = (EditText) findViewById(R.id.et_Cred_Description);

        // Get the viewModel
        viewModel = ViewModelProviders.of(this).get(AddCredentialViewModel.class);

        mSaveUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Get the login and description
                String login = mLoginEditText.getText().toString();
                String description = mDescriptionEditText.getText().toString();

                if (login == null || login.isEmpty()) {
                    Toast.makeText(AddCredentialActivity.this, "Login cannot be empty", Toast.LENGTH_SHORT).show();
                    mLoginEditText.requestFocus();
                    return;
                } else if (description == null || description.isEmpty()) {
                    Toast.makeText(AddCredentialActivity.this, "Description cannot be empty", Toast.LENGTH_SHORT).show();
                    mDescriptionEditText.requestFocus();
                    return;
                }


                // Get the credential to store
                CredentialBuilder builder = new CredentialBuilder();
                Credential credential = builder.setLogin(login).setDescription(description).build();

                // If update,
                if (formStatus == FormStatus.UPDATE) {
                    credential.setCid(updateCredId);
                }

                // Save the credentials
                viewModel.addCredential(credential, getMessageHelper());

                finish();
            }
        });

        // Check whether this is a new Credential form or an update form
        if (getIntent().getExtras().get("credId") != null) {
            // This is an existing credential
            formStatus = FormStatus.UPDATE;

            // Update the save button label
            mSaveUpdateBtn.setText("Update");
            //Disable the update and enable it after the fields are filled
            mSaveUpdateBtn.setEnabled(false);
            Integer cID = (Integer) getIntent().getExtras().get("credId");
            updateCredId = cID;
            // read the credential asynchronously and update the form
            viewModel.readCredential(cID, getFormFiller());
        }


    }

    /**
     * Return the implementation of the {@link IMessageHelper} to show the toast
     * when process is completed in the view model without explicitly calling
     * a method of the view.
     *
     * @return
     */
    @NonNull
    protected IMessageHelper getMessageHelper() {
        return new IMessageHelper() {
            @Override
            public void showToast(String message) {
                Toast.makeText(AddCredentialActivity.this, "Credential updated.", Toast.LENGTH_SHORT).show();
            }
        };
    }

    /**
     * Return the implementation of the {@link ICredentialFormFiller} to update the view after
     * the read sync task is completed
     *
     * @return
     */
    @NonNull
    protected ICredentialFormFiller getFormFiller() {
        return new ICredentialFormFiller() {
            @Override
            public void updateViewForCredential(Credential credential) {
                mLoginEditText.setText(credential.getLogin());
                mDescriptionEditText.setText(credential.getDescription());
                // Enable the button after the fields are filled.
                mSaveUpdateBtn.setEnabled(true);
            }
        };
    }

}
