package com.example.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query


@Dao
interface PersonDataDao {
    @Query("SELECT * FROM person")
    fun getAll(): List<Person>

    @Insert
    fun InsertData(person: Person)

}