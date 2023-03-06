package com.example.myapplication.Database

import android.content.Context
import android.util.Log
import com.example.myapplication.utils.Constants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.CountDownLatch

class DbFunctions {

    fun getInfoFromJetpackDatabase(context: Context, tag: String): String{
        val done = CountDownLatch(1)
        val b = StringBuilder()
        GlobalScope.launch {
            val a = DataStoreManager.getStringValue(context, tag, Constants.DEFAULT_VALUE)
            b.append(a)
            done.countDown()
        }
        done.await()
        return b.toString()
    }

}