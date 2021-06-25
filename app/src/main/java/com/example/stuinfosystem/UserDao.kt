package com.example.stuinfosystem

import androidx.room.*

@Dao
interface UserDao {
    @Query("select * from User where userID =:userID and password = :password ")
    fun selectOneByNameAndPassword(userID:Int,password:String):User

    @Query("select * from User")
    fun selectAll():List<User>

    @Insert
    fun insert(user:User):Long

    @Update
    fun update(user: User)

    @Query("select * from User where userID=:userID")
    fun selectOneById(userID: Int):User

    @Delete
    fun deleteOne(user:User)

}