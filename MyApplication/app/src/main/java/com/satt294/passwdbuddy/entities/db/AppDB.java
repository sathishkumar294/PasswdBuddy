package com.satt294.passwdbuddy.entities.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.satt294.passwdbuddy.entities.DAO.ICredDAO;
import com.satt294.passwdbuddy.entities.entity.Credential;

/**
 * The application Database. Singleton.
 * <p>
 * Created by sathish on 7/30/17.
 */
@Database(entities = {Credential.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    /**
     * Singleton instance
     */
    private static AppDB instance = null;

    /**
     * DAO for credentials class
     */
    public abstract ICredDAO getCredDAO();

    /**
     * Returns the AppDB instance.
     *
     * @param context
     * @return
     */
    public static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class, "PBDB").build();
        }
        return instance;
    }
}
