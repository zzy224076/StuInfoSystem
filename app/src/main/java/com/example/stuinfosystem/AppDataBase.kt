package com.example.stuinfosystem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.entity.User

@Database(entities = [User::class],version =2)
abstract class AppDataBase:RoomDatabase() {
    abstract fun userDao(): UserDao


    companion object{
        private var instance:AppDataBase?=null
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN u_name TEXT  ")
            }
        }
        @Synchronized
        fun getDatabase(context: Context):AppDataBase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java,"app_database")
                .allowMainThreadQueries().addMigrations(MIGRATION_1_2).build().apply { instance = this }
        }
    }


}