package com.example.stuinfosystem.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stuinfosystem.entity.Student
import kotlinx.coroutines.selects.select

@Dao
interface StuDao {
    @Insert
    fun insert(student:Student)
    @Query("select * from student")
    fun queryAll():MutableList<Student>

}