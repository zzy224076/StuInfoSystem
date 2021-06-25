package com.example.stuinfosystem

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Student")
data class Student(
    @PrimaryKey(autoGenerate = true)
    var studentID:Int,
    @ColumnInfo(name = "s_name")
    var stuName: String,
    @ColumnInfo(name = "s_sex")
    var stuSex:String

)
