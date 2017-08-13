package com.satt294.passwdbuddy.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;
import com.satt294.passwdbuddy.helpers.ICredentialFormFiller;
import com.satt294.passwdbuddy.helpers.IMessageHelper;

/**
 * Created by sathish on 8/13/17.
 */

public class AddCredentialViewModel extends AndroidViewModel {

    private AppDB appDB;


    public AddCredentialViewModel(Application application) {
        super(application);

        appDB = AppDB.getInstance(this.getApplication());
    }

    /**
     * Add a credential to the DB asynchronously
     *
     * @param credential
     */
    public void addCredential(Credential credential, @NonNull IMessageHelper messageHelper) {
        new AddAsyncTask(appDB, messageHelper).execute(credential);
    }

    static class AddAsyncTask extends AsyncTask<Credential, Void, Void> {

        private AppDB appDB;
        private IMessageHelper messageHelper;

        public AddAsyncTask(AppDB appDB, IMessageHelper messageHelper) {
            this.appDB = appDB;
            this.messageHelper = messageHelper;
        }

        @Override
        protected Void doInBackground(Credential... credentials) {
            appDB.getCredDAO().save(credentials[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void dbID) {
            messageHelper.showToast("Saved the credential in the database.");
        }
    }

    /**
     * Asynchronously read the credential from DB using the ID and update the view
     * accordingly
     *
     * @param cId
     * @param formFiller
     */
    public void readCredential(Integer cId, @NonNull ICredentialFormFiller formFiller) {
        new ReadAsyncTask(appDB, formFiller).execute(cId);
    }

    static class ReadAsyncTask extends AsyncTask<Integer, Void, Credential> {
        private AppDB appDB;
        private ICredentialFormFiller formFiller;

        public ReadAsyncTask(AppDB appDB, ICredentialFormFiller formFiller) {
            this.appDB = appDB;
            this.formFiller = formFiller;
        }


        @Override
        protected Credential doInBackground(Integer... ids) {
            return appDB.getCredDAO().getById(ids[0]);
        }

        @Override
        protected void onPostExecute(Credential credential) {
            formFiller.updateViewForCredential(credential);
        }
    }

}
