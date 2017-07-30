package com.satt294.passwdbuddy.entities.entity;

import java.lang.ref.SoftReference;

/**
 * Class used to build Credential objects
 */
public class CredentialBuilder {

    private Credential credential = null;

    public CredentialBuilder() {
        credential = new Credential();
    }

    /**
     * Set the login url or username for the credential
     *
     * @param login
     * @return
     */
    public CredentialBuilder setLogin(String login) {
        credential.setLogin(login);
        return this;
    }

    /**
     * Set the password or the password description for the credential
     *
     * @param description
     * @return
     */
    public CredentialBuilder setDescription(String description) {
        credential.setDescription(description);
        return this;
    }

    /**
     * Set the DB id of the credential from DB if it has been already stored
     *
     * @param cid
     * @return
     */
    public CredentialBuilder setId(int cid) {
        credential.setCid(cid);
        return this;
    }

    public CredentialBuilder setURLs(String urls) {
        credential.setUrls(urls);
        return this;
    }

    /**
     * Returns the credential built so far.
     *
     * @return
     */
    public Credential build() {
        return credential;
    }
}
