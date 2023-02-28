package com.example.myapplication.Database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert
    fun insertUserDetails(UserDetailsDB: UserDetailsDB)

    @Query("SELECT * FROM UserDetailsDB")
    fun getAllUserDetails(): Flow<List<UserDetailsDB>>
//
//    @Query("SELECT login FROM UserDetailsDB")
//    fun getLogin(): Flow<List<UserDetailsDB>>
//
//    @Query("SELECT password FROM UserDetailsDB")
//    fun getPassword(): Flow<List<UserDetailsDB>>

    @Query("DELETE FROM UserDetailsDB")
    fun deleteAll()


}