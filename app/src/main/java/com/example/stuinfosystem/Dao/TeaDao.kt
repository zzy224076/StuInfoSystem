package com.example.stuinfosystem.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.Teacher

@Dao
interface TeaDao {
    @Insert
    fun insert(teacher: Teacher)
    @Query("select * from teacher")
    fun queryAll():MutableList<Teacher>

}