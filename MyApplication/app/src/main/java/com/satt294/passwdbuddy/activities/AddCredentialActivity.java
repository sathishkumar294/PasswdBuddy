package com.satt294.passwdbuddy.activities;

import android.arch.lifecycle.ViewModelProviders;
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
import com.satt294.passwdbuddy.helpers.IMessageHelper;
import com.satt294.passwdbuddy.viewmodels.AddCredentialViewModel;

public class AddCredentialActivity extends AppCompatActivity {

    private EditText mLoginEditText;
    private EditText mDescriptionEditText;
    private Button mSaveUpdateBtn;


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
                    // TODO: P1: Ask the user to fix login
                } else if (description == null || description.isEmpty()) {
                    // TODO: P1: Ask the user to fix description
                }

                // Get the credential to store
                CredentialBuilder builder = new CredentialBuilder();
                Credential credential = builder.setLogin(login).setDescription(description).build();

                // Save the credentials
                viewModel.addCredential(credential, getMessageHelper());

                finish();
            }
        });

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
                Toast.makeText(AddCredentialActivity.this, "Saved the credential in DB.", Toast.LENGTH_SHORT).show();
            }
        };
    }

}
