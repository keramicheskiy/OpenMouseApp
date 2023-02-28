package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class StartScreen: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_screen)
        val tv = findViewById<TextView>(R.id.textView)
        tv.setOnClickListener{
            val intend = Intent(this@StartScreen, MainActivity::class.java)
            startActivity(intend)
        }

    }
}