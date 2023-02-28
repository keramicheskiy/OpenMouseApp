package com.example.myapplication.SQLiteDB


import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class MyDbManager(val context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb() {
        db = myDbHelper.writableDatabase
    }

    fun insertUserDetailsToDB(login: String, password: String) {
        val values = ContentValues().apply{
            put(MyDbNameClass.COLUMN_NAME_TITLE, login)
            put(MyDbNameClass.COLUMN_NAME_SUBTITLE, password)
        }
        db?.insert(MyDbNameClass.TABLE_NAME, null, values)

    }

//    fun readDbData(): String {
//        var data: String = ""
//        val cursor = db?.query(
//            MyDbNameClass.TABLE_NAME,
//            null,
//            null,
//            null,
//            null,
//            null,
//            null
//        )
//        while (cursor?.isLast()!!) {
//            val firstName = cursor?.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_TITLE))
//            val lastName = cursor?.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_SUBTITLE))
//            data = "${firstName.toString()},${lastName.toString()}"
//        }
//        cursor.close()
//        return data
//    }


    fun readUserDetailsFromDB(): ArrayList<UserDetailsInfo> {
        var firstName: String
        var lastName: String
        var db = myDbHelper.readableDatabase
        var personList: ArrayList<UserDetailsInfo> = ArrayList()

        val cursor = db?.rawQuery("SELECT * FROM ${MyDbNameClass.TABLE_NAME};", null)
        if (cursor?.moveToFirst()!!) {
            do {
                firstName = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_TITLE))
                lastName = cursor.getString(cursor.getColumnIndexOrThrow(MyDbNameClass.COLUMN_NAME_SUBTITLE))
                val UserDetailsInfo = UserDetailsInfo(
                    firstName,
                    lastName
                )
                personList.add(UserDetailsInfo)
            }while(cursor.moveToNext())
        }
        cursor.close()
        return personList
    }



    fun deleteAllFromDb() {
        db?.execSQL("DELETE FROM ${MyDbNameClass.TABLE_NAME};")
    }




    fun closeDb() {
        myDbHelper.close()
    }


}