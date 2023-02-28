package com.example.myapplication.SQLiteDB

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserDetailsInfo (
    val login: String = "-",
    val password: String = "-"

) : Parcelable

