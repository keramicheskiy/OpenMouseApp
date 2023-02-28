package com.example.myapplication

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.widget.TextView
import kotlin.math.roundToInt

class NotImportant {
    //    val text1 = findViewById<TextView>(R.id.tvSensorSmall1)
//        var mode = 0
//        var check1 = 0
//        var isAlreadyWas1 = false
//        var isAlreadyWas2 = false
//                check1 += 1
//
//                if(isCalibrateButtonWasClicked){
//                    when(check1) {
//                        1 -> {
//                            if (!isAlreadyWas1){
//                                calibratedValue[0] = sensorValue
//                                check--
//                                mode = 0
//                            }
//
//                        }
//
//                        2 -> {
//                            if (!isAlreadyWas2) {
//                                calibratedValue[1] = sensorValue
//                                check--
//                                mode = 1
//                            }
//                        }
//                    }
//                }
//                if (check == 0) {
//                    isCalibrateButtonWasClicked = false
//                    isAlreadyWas1 = false
//                    isAlreadyWas2 = false
//                }
//
//
//                if (check1 == 2) {
//                    check1 = 0
//                }
//
//                text1.text = calibratedValue.toString()

//    fun showAngleX(path: Int, sensorName: Int){
//        val tvSensor = findViewById<TextView>(path)
//        val sListener = object : SensorEventListener {
//            override fun onSensorChanged(sEvent: SensorEvent?) {
//                val value = sEvent?.values
//                val sensorValueX = value?.get(0).toString().toFloat().roundToInt()
//
//                if (isBtnCalibrateWasClickedX) {
//                    calibratedValueX = sensorValueX
//                    isBtnCalibrateWasClickedX = false
//                }
//
//                val sData = sensorValueX - calibratedValueX
//                tvSensor.text = "${sData}°"
//            }
//            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
//        }
//        getSensorInfo(sListener, sensorName)
//    }
//
//
//    fun showAngleY(path: Int, sensorName: Int) {
//        val tvSensor = findViewById<TextView>(path)
//
//        val sListener = object : SensorEventListener {
//            override fun onSensorChanged(sEvent: SensorEvent?) {
//                val value = sEvent?.values
//                val sensorValueY = (value?.get(1).toString().toFloat() * 9).roundToInt()
//
//                if (isBtnCalibrateWasClickedY) {
//                    calibratedValueY = sensorValueY
//                    isBtnCalibrateWasClickedY = false
//                }
//
//                val sData = sensorValueY - calibratedValueY
//                tvSensor.text =  "${sData}°"
//            }
//            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
//        }
//        getSensorInfo(sListener, sensorName)
//    }

}