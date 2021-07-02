package com.example.stuinfosystem.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Course(
    @PrimaryKey(autoGenerate = true)
    var ID:Int,
    @ColumnInfo(name = "course_name")
    var courseName:String,
    @ColumnInfo(name = "t_name")
    var teaName:String,
    @ColumnInfo(name = "c_class")
    var cClass: String
)
