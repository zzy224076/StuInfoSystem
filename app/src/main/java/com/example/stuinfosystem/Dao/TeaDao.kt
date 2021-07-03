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
    fun queryAll(): MutableList<Teacher>

    @Query("select * from teacher where t_name=:tName")
    fun queryOneByName(tName: String): Teacher

    @Query("delete from teacher where teacherID=:id")
    fun deleteOneById(id: Int)

}