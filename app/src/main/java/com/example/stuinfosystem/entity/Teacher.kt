package com.example.stuinfosystem.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Teacher")
data class Teacher(
    @PrimaryKey()
    var teacherID:Int,
    @ColumnInfo(name = "t_name")
    var teaName:String?,
    @ColumnInfo(name = "t_sex")
    var teaSex: String?,
    @ColumnInfo(name = "t_tel")
    var teaTel:String?,
    @ColumnInfo(name = "t_class")
    var teaClass:String?
)
