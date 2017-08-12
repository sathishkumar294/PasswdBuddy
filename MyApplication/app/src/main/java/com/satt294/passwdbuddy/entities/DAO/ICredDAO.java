package com.satt294.passwdbuddy.entities.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.satt294.passwdbuddy.entities.entity.Credential;

import java.util.List;

/**
 * DAO Class for {@link Credential} objects.
 * Created by sathish on 7/30/17.
 */

@Dao
public interface ICredDAO {

    @Query("SELECT * FROM CREDS")
    LiveData<List<Credential>> getAll();

    @Query("SELECT * FROM CREDS where DESCRIPTION LIKE :description")
    List<Credential> getAllByLogin(String description);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(Credential cred);

    @Update
    void update(Credential cred);

    @Delete
    void remove(Credential cred);

    @Query("SELECT * FROM CREDS WHERE cid=:cId")
    Credential getById(int cId);

}
