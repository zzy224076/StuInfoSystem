package com.example.stuinfosystem

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.stuinfosystem.Dao.StuDao
import com.example.stuinfosystem.Dao.UserDao
import com.example.stuinfosystem.entity.Student
import com.example.stuinfosystem.entity.User

@Database(entities = [User::class,Student::class],version =3)
abstract class AppDataBase:RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun stuDao(): StuDao

    companion object{
        private var instance:AppDataBase?=null
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE user ADD COLUMN u_name TEXT  ")
            }
        }
        val MIGRATION_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("Create table IF NOT EXISTS 'Student'('studentID' INTEGER NOT null PRIMARY KEY ,'s_name' text,'s_sex' text,'stu_tel' text,'stu_class' text)")
                //CREATE TABLE `Fruit` (`id` INTEGER, `name` TEXT, " +
                //                "PRIMARY KEY(`id`))
            }
        }
        @Synchronized
        fun getDatabase(context: Context):AppDataBase{
            instance?.let {
                return it
            }
            return Room.databaseBuilder(context.applicationContext,
                AppDataBase::class.java,"app_database")
                .allowMainThreadQueries().addMigrations(MIGRATION_1_2, MIGRATION_2_3).build().apply { instance = this }
        }
    }


}