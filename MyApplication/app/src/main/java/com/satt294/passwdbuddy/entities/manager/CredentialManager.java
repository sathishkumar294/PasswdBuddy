package com.satt294.passwdbuddy.entities.manager;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;

import java.util.List;

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

}
