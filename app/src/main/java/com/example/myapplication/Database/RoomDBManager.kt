//package com.example.myapplication.Database
//
//import android.content.Context
//import com.example.myapplication.Database.Dao
//import kotlinx.coroutines.flow.Flow
//
//class RoomDBManager {
//
//
//    public fun insertUserDetailsInDB(db: MainDB, login: String, password: String) {
//        val UserDetailsDB = UserDetailsDB(null,
//            login,
//            password
//        )
//
//
//        Thread{
//            db.getDao().insertUserDetails(UserDetailsDB)
//        }.start()
//    }
//
//    public fun deleteAllRows(db: MainDB) {
//        db.getDao().deleteAll()
//    }
//
//    public fun getLogin(db: MainDB): Flow<List<UserDetailsDB>> {
//        return db.getDao().getLogin()
//    }
//
//    public fun getPassword(db: MainDB): Flow<List<UserDetailsDB>> {
//        return db.getDao().getPassword()
//    }
//
//}