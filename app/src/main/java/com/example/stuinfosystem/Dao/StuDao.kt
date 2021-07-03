package com.example.stuinfosystem.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.stuinfosystem.entity.Student
import kotlinx.coroutines.selects.select

@Dao
interface StuDao {
    @Insert
    fun insert(student: Student)

    @Query("select * from student")
    fun queryAll(): MutableList<Student>

    @Delete
    fun delete(student: Student)

    @Query("delete from student where studentID = :id")
    fun deleteOneById(id:Int)

    @Query("select * from student where stu_class = :className")
    fun queryStuByClassname(className:String):MutableList<Student>

}