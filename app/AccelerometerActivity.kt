package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.TextView

open class AccelerometerActivity: AppCompatActivity(){
    private fun findNorthOrientation(path1: ): SensorEventListener {
        val tvSensor = findViewById<TextView>(path1)
        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sData = "Угл к северу: ${value?.get(0).toString().toFloat().roundToInt()}°\n" +
                        "${value?.get(1).toString().toFloat().roundToInt()}°\n" +
                        "${value?.get(2).toString().toFloat().roundToInt()}°\n"
//                tvSensor1.text = sData
                tvSensor.text = sData
            }

            override fun onAccuracyChanged(sEvent: Sensor?, p1: Int) {

            }
        }
        return sListener
    }

}