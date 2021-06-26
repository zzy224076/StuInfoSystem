package com.example.stuinfosystem.Dao

import androidx.room.*
import com.example.stuinfosystem.entity.User

@Dao
interface UserDao {
    @Query("select * from User where userID =:userID and password = :password ")
    fun selectOneByNameAndPassword(userID:Int,password:String): User

    @Query("select * from User")
    fun selectAll():MutableList<User>

    @Insert
    fun insert(user: User):Long

    @Update
    fun update(user: User)

    @Query("select * from User where userID=:userID")
    fun selectOneById(userID: Int): User

    @Delete
    fun deleteOne(user: User)

}