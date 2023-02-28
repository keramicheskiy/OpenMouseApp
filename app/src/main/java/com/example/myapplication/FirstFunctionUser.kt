package com.example.myapplication
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FirstFunctionUser (
    val id: String = "-",
    val login: String = "-",
    val password: String = "-"

) : Parcelable

