package com.satt294.passwdbuddy.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.helpers.IMessageHelper;

import java.util.List;

/**
 * View Model class to hold the credential data.
 * It is independent of the Activity (view) lifecycle,
 * hence it can be used to hold data independent of view updates
 * <p>
 * Created by sathish on 8/12/17.
 */

public class ListCredentialViewModel extends AndroidViewModel {

    /**
     * List of Credentials
     */
    private final LiveData<List<Credential>> credentialsLiveList;

    /**
     * The application database
     */
    private AppDB appDB;


    /**
     * Reference: https://android.jlelse.eu/android-architecture-components-room-livedata-and-viewmodel-fca5da39e26b
     */

    public ListCredentialViewModel(Application application) {
        super(application);

        // Get the appDB instance
        appDB = AppDB.getInstance(this.getApplication());

        // Get the live data
        credentialsLiveList = appDB.getCredDAO().getAll();
    }

    /**
     * Expose data to views
     *
     * @return
     */
    public LiveData<List<Credential>> getCredentialsLiveList() {
        return credentialsLiveList;
    }

    /**
     * Remove a credential asynchronously.
     *
     * @param credential
     */
    public void deleteCredential(Credential credential, @NonNull IMessageHelper messageHelper) {
        new DeleteAsyncTask(appDB, messageHelper).execute(credential);
    }

    private static class DeleteAsyncTask extends AsyncTask<Credential, Void, Void> {
        private AppDB appDB;
        private IMessageHelper messageHelper;

        DeleteAsyncTask(AppDB appDB, IMessageHelper messageHelper) {
            this.appDB = appDB;
            this.messageHelper = messageHelper;
        }

        @Override
        protected Void doInBackground(Credential... credentials) {
            appDB.getCredDAO().delete(credentials[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            messageHelper.showToast("Credential removed from database.");
        }
    }
}
