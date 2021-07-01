package com.example.stuinfosystem.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Student")
data class Student(
    @PrimaryKey()
    var studentID:Int,
    @ColumnInfo(name = "s_name")
    var stuName: String?,
    @ColumnInfo(name = "s_sex")
    var stuSex:String?,
    @ColumnInfo(name = "stu_tel")
    var stuTel:String?,
    @ColumnInfo(name = "stu_class")
    var stuClass:String?

)
