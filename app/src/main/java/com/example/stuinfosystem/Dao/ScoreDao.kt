package com.example.stuinfosystem.Dao

import android.accessibilityservice.AccessibilityService
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.stuinfosystem.entity.Score


@Dao
interface ScoreDao {

    @Insert()
    fun insert(score: Score)

    @Query("select * from score")
    fun queryAll(): MutableList<Score>

    @Query("select * from Score where stu_id=:stuId and course_name=:courseName")
    fun queryOneByStuIdAndCourseName(stuId: Int,courseName: String): Score

    @Query("update score set s_score=:score where stu_id=:stuId and course_name =:courseName ")
    fun updateScoreByStuIdAndCourseName(score:Int,stuId:Int,courseName: String)

    @Query("select * from score where stu_name=:stuName")
    fun queryByStuId(stuName: String):MutableList<Score>
}