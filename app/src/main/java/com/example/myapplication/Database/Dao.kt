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
    @Query("SELECT PrivateUserDetails FROM UserDetailsDB")
    fun getPrivateUserDetails(): Flow<List<UserDetailsDB>>

    @Query("DELETE FROM UserDetailsDB")
    fun deleteAll()


}