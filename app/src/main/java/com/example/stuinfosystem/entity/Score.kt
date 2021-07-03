package com.example.stuinfosystem.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Score")
data class Score(
    @PrimaryKey(autoGenerate = true)
    var s_id:Int,
    @ColumnInfo(name = "stu_id")
    var stuId:Int,
    @ColumnInfo(name ="stu_name" )
    var stuName:String,
    @ColumnInfo(name = "tea_id")
    var teaId:Int,
    @ColumnInfo(name = "tea_name")
    var teaName: String,
    @ColumnInfo(name = "course_name")
    var courseName:String,
    @ColumnInfo(name = "s_score")
    var sScore:Int
)
