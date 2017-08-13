package com.satt294.passwdbuddy.helpers;

import com.satt294.passwdbuddy.entities.entity.Credential;

/**
 * Created by sathish on 8/13/17.
 */

public interface ICredentialFormFiller {
    /**
     * Function to update the Credential form details
     *
     * @param credential
     */
    void updateViewForCredential(Credential credential);
}
