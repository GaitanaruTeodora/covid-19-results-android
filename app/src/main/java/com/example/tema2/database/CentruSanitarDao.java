package com.example.tema2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CentruSanitarDao {
    @Insert
    long insert(CentruSanitar centru);

    @Query("select * from centreSanitare")
    List<CentruSanitar> getAll();

}
