package com.example.myapplication.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = [UserDetailsDB::class,], version = 2)
abstract class MainDB : RoomDatabase() {

    abstract fun getDao(): Dao

    companion object{
        fun getDB(context : Context): MainDB {
            return Room.databaseBuilder(
                context.applicationContext,
                MainDB::class.java,
                "test.db"
            ).build()
        }
    }


}