package com.example.stuinfosystem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
    @PrimaryKey
    var userID:Int,
    @ColumnInfo(name = "password")
    var password:String,
    @ColumnInfo(name="u_name")
    var name:String?,
    @ColumnInfo(name = "u_type")
    var type:Int
)
