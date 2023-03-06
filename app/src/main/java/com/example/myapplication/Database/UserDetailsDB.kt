package com.example.myapplication.Database


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "UserDetailsDB")
data class UserDetailsDB(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "PrivateUserDetails")
    var PrivateUserDetails: String

)
