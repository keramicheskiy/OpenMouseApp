package com.example.myapplication.utils

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.widget.TextView
import com.example.myapplication.BaseActivity
import com.example.myapplication.R
import com.example.myapplication.STORAGE

open class AccelerometerActivity : BaseActivity() {
    var calibratedValueX = 0
    var calibratedValueY = 0
    var isBtnCalibrateWasClickedX: Boolean = false
    var isBtnCalibrateWasClickedY: Boolean = false
    var dataX: Int = 0
    var isActivityClosed: Boolean = false



    fun showAngle(path: Int, showMode: Char, setValue: (FloatArray?) -> Int): SensorEventListener {
        val mode = showMode.lowercase()
        val tvSensor = findViewById<TextView>(path)

        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sensorValue = setValue(value)

                defineCalibrationIfX(mode, sensorValue)
                defineCalibrationIfY(mode, sensorValue)
                val sData = getCalibratedValue(sensorValue, mode).toString()
                tvSensor.text = "${(sData.replaceLast(sData.get(sData.length-1).toString(),
                    "." + sData.get(sData.length-1).toString())).getNormalFloat()}°"
            }
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
        }
        return sListener
    }


    fun getSensorInfo(sListener: SensorEventListener, sensorName: Int) {
        val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(sensorName)
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_GAME)
    }


    fun getCalibratedValue(sensorValue1: Int, mode: String): Int{
        val tvSensorRealValueX = findViewById<TextView>(R.id.tvSensorRealValueX)
        val tvSensorRealValueY = findViewById<TextView>(R.id.tvSensorRealValueY)
        var sData: Int = 0
        var sensorValue = sensorValue1.toString()

        var value = (sensorValue.replaceLast
            (sensorValue[sensorValue.length-1].toString(),
            ("." + sensorValue[sensorValue.length-1].toString()))
                ).getNormalFloat()

        when(mode) {
            "x" -> {
                sData = sensorValue1 - calibratedValueX
                tvSensorRealValueX.text = "${value}°"
            }
            "y" -> {
                sData = sensorValue1 - calibratedValueY
                tvSensorRealValueY.text = "${value}°"
            }
        }
        return sData
    }

    fun defineCalibrationIfX(mode: String, sensorValue: Int) {
        if (isBtnCalibrateWasClickedX && mode == "x") {
            if (sensorValue + STORAGE.MaxDeflectAngle * 10 < 3600 &&
                sensorValue - STORAGE.MaxDeflectAngle * 10 > 0) {
                calibratedValueX = sensorValue
            } else {
                showErrorSnackBar("Невозможно совершить калибровку", true)
            }
            isBtnCalibrateWasClickedX = false
        }
    }
    fun defineCalibrationIfY(mode: String, sensorValue: Int) {
        if (isBtnCalibrateWasClickedY && mode == "y") {
            calibratedValueY = sensorValue
            isBtnCalibrateWasClickedY = false
        }
    }




}

