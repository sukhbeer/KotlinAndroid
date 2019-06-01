package com.example.room

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class Person (

    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name ="age")
    var age : Int
)/*{
    constructor():this(null,"",0)
}*/

