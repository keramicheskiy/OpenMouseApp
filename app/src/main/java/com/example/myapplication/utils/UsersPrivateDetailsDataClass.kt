package com.example.myapplication.utils

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

@Serializable
data class UsersPrivateDetailsDataClass(
    val login: String = "",
    val password: String = ""
)