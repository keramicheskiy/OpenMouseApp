package com.example.myapplication.SQLiteDB

import android.provider.BaseColumns

object MyDbNameClass : BaseColumns{
    const val TABLE_NAME = "UserDetails"
    const val COLUMN_NAME_TITLE = "login"
    const val COLUMN_NAME_SUBTITLE = "password"
    const val DATABASE_VERSION = 1
    const val DATABASE_NAME = "MyDb.db"

    const val CREATE_TABLE  =
        "CREATE TABLE IF NOT EXISTS ${MyDbNameClass.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${MyDbNameClass.COLUMN_NAME_TITLE} TEXT," +
                "${MyDbNameClass.COLUMN_NAME_SUBTITLE} TEXT)"

    const val DELETE_TABLE = "DROP TABLE IF EXISTS ${MyDbNameClass.TABLE_NAME}"



}