package com.example.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

@Database(entities = [Person::class],version = 1)

abstract class PersonDatabase : RoomDatabase(){
    abstract fun personDao(): PersonDataDao

    companion object{
        private var INSTANCE : PersonDatabase? = null

        fun getInstance(c: Context): PersonDatabase? {
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(c.applicationContext,
                    PersonDatabase::class.java,"person.DB").build()
            }
            return INSTANCE
        }

        fun destroyInstance(){
            INSTANCE = null
        }
    }
}