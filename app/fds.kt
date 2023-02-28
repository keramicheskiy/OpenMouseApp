package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
//import com.example.shop111.StartScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeButton.setOnClickListener() {
            accelerometerInfo()
        }

    }


}
private fun accelerometerInfo(){
//        val tvSensor = findViewById<TextView>(R.id.tvSensor)
    sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val sensor1 = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    val sListener = object : SensorEventListener {
        override fun onSensorChanged(sEvent: SensorEvent?) {
            val value = sEvent?.values
            val sData = "X: ${value?.get(0)} \n" +
                    "Y: ${value?.get(1)} \n" +
                    "Z: ${value?.get(2)}"
            tvSensor.text = sData
        }

        override fun onAccuracyChanged(sEvent: Sensor?, p1: Int) {

        }
        sManager.registerListener(sListener, sensor1, SensorManager.SENSOR_DELAY_NORMAL)
    }
}



package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.myapplication.databinding.ActivityMainBinding
//import com.example.shop111.StartScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sManager: SensorManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val tvSensor = findViewById<TextView>(R.id.tvSensor)
        sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor1 = sManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sData = "X: ${value?.get(0)} \n" +
                        "Y: ${value?.get(1)} \n" +
                        "Z: ${value?.get(2)}"
                tvSensor.text = sData
            }

            override fun onAccuracyChanged(sEvent: Sensor?, p1: Int) {

            }
        }
        sManager.registerListener(sListener, sensor1, SensorManager.SENSOR_DELAY_NORMAL)
    }


}

