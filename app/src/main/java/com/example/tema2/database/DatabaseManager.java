package com.example.tema2.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {CentruSanitar.class}, exportSchema = false, version = 1)
public abstract class DatabaseManager extends RoomDatabase {

    public static final String CENTRU_SANITAR_DB = "CentruSanitar_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, CENTRU_SANITAR_DB)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return databaseManager;
    }

    public abstract CentruSanitarDao getCentruDao();

}
