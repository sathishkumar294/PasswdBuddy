package com.satt294.passwdbuddy.entities.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Entity class that stores a credential.
 * <p>
 * Created by sathish on 7/30/17.
 */
@Entity(tableName = "CREDS")
public class Credential {

    @PrimaryKey(autoGenerate = true)
    private Integer cid;

    @ColumnInfo(name = "LOGIN")
    private String login;

    @ColumnInfo(name = "DESCRIPTION")
    private String description;

    @ColumnInfo(name = "URLS")
    private String urls;

    public Credential(String login, String description, String urls) {
        this.login = login;
        this.description = description;
        this.urls = urls;
    }

    @Ignore
    public Credential(String login, String description) {
        this.login = login;
        this.description = description;
    }

    @Ignore
    public Credential() {
    }

    /***************************
     * GETTERS and SETTERS     *
     ***************************/
    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "cid=" + cid +
                ", login='" + login + '\'' +
                ", description='" + description + '\'' +
                ", urls='" + urls + '\'' +
                '}';
    }
}

