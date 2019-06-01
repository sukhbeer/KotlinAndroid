package com.example.android.room;

import android.arch.lifecycle.LiveData;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MyDao  {

    @Insert
    void insert(User user);

    @Query("DELETE FROM word_table")
    void deleteAll();

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<User>> getAllWords();


}
