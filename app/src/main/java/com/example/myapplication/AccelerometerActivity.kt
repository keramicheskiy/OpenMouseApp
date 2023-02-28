package com.example.myapplication

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.roundToInt
import kotlin.properties.Delegates

open class AccelerometerActivity : IntentingControl() {
    var calibratedValueX = 0
    var calibratedValueY = 0
    var isBtnCalibrateWasClickedX: Boolean = false
    var isBtnCalibrateWasClickedY: Boolean = false
    var dataX: Int = 0
    var isActivityClosed: Boolean = false



    fun showAngle(path: Int, showMode: Char, setValue: (FloatArray?) -> Int): SensorEventListener {
        val mode = showMode.lowercase()
        val tvSensor = findViewById<TextView>(path)
//        val database = Firebase.database
//        val myRef = database.getReference("test/xxx")


        val sListener = object : SensorEventListener {
            override fun onSensorChanged(sEvent: SensorEvent?) {
                val value = sEvent?.values
                val sensorValue = setValue(value)

                if (isBtnCalibrateWasClickedX && mode == "x") {
                    if (sensorValue + STORAGE.MaxDeflectAngle * 10 < 3600 &&
                        sensorValue - STORAGE.MaxDeflectAngle * 10 > 0) {
                        calibratedValueX = sensorValue
                    } else {
                        showErrorSnackBar("Невозможно совершить калибровку", true)
                    }
//                    calibratedValueX = sensorValue
                    isBtnCalibrateWasClickedX = false

                }
                if (isBtnCalibrateWasClickedY && mode == "y") {
                    calibratedValueY = sensorValue
                    isBtnCalibrateWasClickedY = false
                }

                val sData = getCalibratedValue(sensorValue, mode)

//                AngleX.getStringFromIndexToIndex(0, AngleX.length-2) + "," + AngleX.get(AngleX.length-1)

                if (true) {
                    tvSensor.text = "${sData.toString().getStringFromIndexToIndex(0, sData.toString().length-2) + "," + sData.toString().get(sData.toString().length-1)}°"
                }
            }
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
        }
        return sListener
    }


    fun getSensorInfo(sListener: SensorEventListener, sensorName: Int) {
        val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor = sManager.getDefaultSensor(sensorName)
        sManager.registerListener(sListener, sensor, SensorManager.SENSOR_DELAY_GAME)


//        sManager.unregisterListener(sListener)
//        val back = findViewById<Button>(R.id.btnCalibrateSensors)
//
//        back.text = sManager.getSensorList(Sensor.TYPE_ORIENTATION).toString()

    }

    fun setValue(value: FloatArray?): Int {
        return 0
    }

    fun getCalibratedValue(sensorValue: Int, mode: String): Int{
        val tvSensorRealValueX = findViewById<TextView>(R.id.tvSensorRealValueX)
        val tvSensorRealValueY = findViewById<TextView>(R.id.tvSensorRealValueY)


        var sData: Int = 0
        when(mode) {
            "x" -> {
                sData = sensorValue - calibratedValueX
                tvSensorRealValueX.text = "${sensorValue.toString().getStringFromIndexToIndex(0, sensorValue.toString().length-2) + "," + sensorValue.toString().get(sensorValue.toString().length-1)}°"
            }
            "y" -> {
                sData = sensorValue - calibratedValueY
                tvSensorRealValueY.text = "${sensorValue.toString().getStringFromIndexToIndex(0, sensorValue.toString().length-2) + "," + sensorValue.toString().get(sensorValue.toString().length-1)}°"
            }
        }
        return sData
    }


}