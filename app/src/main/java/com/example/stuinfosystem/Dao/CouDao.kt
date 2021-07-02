package com.example.stuinfosystem.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stuinfosystem.entity.Course

@Dao
interface CouDao {
    @Query("select * from course")
    fun queryAll():MutableList<Course>
    @Insert()
    fun insert(course: Course)
    @Query("select * from course where course_name =:courseName  and c_class = :cclass")
    fun queryByCourseAndClass(courseName:String,cclass:String):Course
}