package com.satt294.passwdbuddy.entities.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;

/**
 * Service layer between the activities and DB.
 * Provides access to Credential objects.<p> Singleton.
 * <p>
 * Created by sathish on 7/30/17.
 */

public class CredentialManager {

    /**
     * Singleton instance
     */
    private static CredentialManager instance = null;

    /**
     * Save the context for future use
     */
    private Context mContext;


    /**
     * Access to DB
     */
    private AppDB appDb = null;

    /**
     * Do not allow creation of a new manager from outside
     */
    private CredentialManager(Context context) {
        appDb = AppDB.getInstance(context);
        mContext = context;
    }

    /**
     * Returns the instance of the Credential Manager
     *
     * @return
     */
    public static CredentialManager getInstance(Context context) {
        if (instance == null) {
            instance = new CredentialManager(context);
        }
        return instance;
    }

    /**
     * Function to insert or update a credential information into DB
     *
     * @param credential
     */
    public void saveOrUpdate(Credential credential) {
        long cid = -1;

        if (credential.getCid() != null) {
            appDb.getCredDAO().update(credential);
        } else {
            new AsyncTask<Credential, Void, Integer>() {

                @Override
                protected Integer doInBackground(Credential... credentials) {
                    Long lCid= appDb.getCredDAO().save(credentials[0]);
                    return lCid.intValue();
                }

                @Override
                protected void onPostExecute(Integer cId) {
                    // Inform the user
                    Toast.makeText(mContext, "Saved the user in DB. (ID: " + cId + ")", Toast.LENGTH_SHORT).show();
                }
            }.execute(credential);


        }


    }
}
