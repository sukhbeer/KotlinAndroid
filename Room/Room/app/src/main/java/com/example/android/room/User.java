package com.example.android.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String mWord;

    public User(@NonNull String mWord) {
        this.mWord = mWord;
    }

    @NonNull
    public String getmWord() {
        return mWord;
    }
}
