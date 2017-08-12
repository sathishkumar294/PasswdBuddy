package com.satt294.passwdbuddy.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.widget.ListView;

import com.satt294.passwdbuddy.entities.db.AppDB;
import com.satt294.passwdbuddy.entities.entity.Credential;

import java.util.List;

/**
 * View Model class to hold the credential data.
 * It is independent of the Activity (view) lifecycle,
 * hence it can be used to hold data independent of view updates
 * <p>
 * Created by sathish on 8/12/17.
 */

public class CredentialViewModel extends AndroidViewModel {

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

    public CredentialViewModel(Application application) {
        super(application);
        credentialsLiveList = null;
    }
}
