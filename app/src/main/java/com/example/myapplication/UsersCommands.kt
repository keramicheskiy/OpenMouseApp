package com.example.myapplication
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsersCommands (
    val clickLeftMouseButton: Boolean = false,
    val clickRightMouseButton: Boolean = false,
    val holdLeftMouseButton: Boolean = false,
    val holdRightMouseButton: Boolean = false,
    val scrollUp: Boolean = false,
    val scrollDown: Boolean = false

    ) : Parcelable

