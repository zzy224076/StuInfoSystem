package com.example.stuinfosystem.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stuinfosystem.entity.Score


@Dao
interface ScoreDao {

    @Insert()
    fun insert(score: Score)

    @Query("select * from score")
    fun queryAll(): MutableList<Score>

    @Query("select * from Score where stu_id=:stuId and course_name=:courseName")
    fun queryOneByStuIdAndCourseName(stuId: Int,courseName: String): Score
}