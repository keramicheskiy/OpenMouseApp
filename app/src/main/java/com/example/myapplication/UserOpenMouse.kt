package com.example.myapplication
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserOpenMouse (
    val uniqueId: Int = 0,
    val login: String = "",
    val password: String = "",
    val coordinateX: String = "",
    val coordinateY: String = "",
    val MaxDeflectAngle: String = "",
    val LastMouseCommand: String = "",
    val didServerHandleCommand: String = "false"

) : Parcelable

