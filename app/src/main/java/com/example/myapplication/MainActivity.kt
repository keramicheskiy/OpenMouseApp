package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.example.myapplication.SQLiteDB.MyDbManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.utils.Constants
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import io.grpc.Context.Storage
import kotlin.math.roundToInt
import kotlin.random.Random

class MainActivity : AccelerometerActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var sManager: SensorManager
    lateinit var mUserDetails: FirstFunctionUser
//    lateinit var login: String
//    lateinit var password: String
    lateinit var myRef: DatabaseReference
    lateinit var sListener1: SensorEventListener
    lateinit var sListener2: SensorEventListener

    val myDbManager = MyDbManager(this)
    val database = Firebase.database
    var lastMouseCommand: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onResume() {
        super.onResume()
        isActivityClosed = false

        myDbManager.openDb()
        val login = myDbManager.readUserDetailsFromDB()[0].login.replace('.', '+')
        val password = myDbManager.readUserDetailsFromDB()[0].password

        myRef = database.getReference("OpenMouse/usersData/${login}")
        binding.AngleX.doOnTextChanged { text, start, before, count ->
            if (binding.SwitchStatus.isChecked) {
                var AngleX = binding.AngleX.text.toString()
                var AngleY = binding.AngleY.text.toString()

                val userOpenMouse = UserOpenMouse(
                    0,
                    login,
                    password,

                    AngleX.replace(".", "")
                        .replace("°", "")
                        .toInt().toString(),

                    AngleY.replace(".", "")
                        .replace("°", "")
                        .toInt().toString(),

                    STORAGE.MaxDeflectAngle.toString(),
                    lastMouseCommand,
                )
                myRef.setValue(userOpenMouse)
            }

        }

        var myRef1 = database.getReference("OpenMouse/usersData")
        onChangeFirebaseListener(myRef1){
//            binding.a.text = it.child("$login").child("password").value.toString()
//            binding.a.text = it.child("$login").value.toString()
        }





        sListener1 = showAngle(R.id.AngleX, 'x') {
            (it?.get(0).toString().toFloat() * 10).roundToInt()
        }
        getSensorInfo(sListener1, Sensor.TYPE_ORIENTATION)


        sListener2 = showAngle(R.id.AngleY, 'y') {
            (it?.get(1).toString().toFloat() * 90).roundToInt()
        }
        getSensorInfo(sListener2, Sensor.TYPE_ACCELEROMETER)


        binding.btnCalibrateSensors.setOnClickListener() {
            isBtnCalibrateWasClickedX = true
            isBtnCalibrateWasClickedY = true
        }
        binding.btnReturnStartValue.setOnClickListener() {
            imgBlink(R.id.btnReturnStartValue)
            calibratedValueX = 0
            calibratedValueY = 0
        }


        binding.btnGetKeyboard.setOnClickListener() {
            imgBlink(R.id.btnGetKeyboard)
//            TODO("СДЕЛАТЬ КЛАВУ")

        }




        if (STORAGE.settings_virtualMouseCheckBoxStatus != "") {
            if (STORAGE.settings_virtualMouseCheckBoxStatus == "INVISIBLE") {
                    binding.virtualMouse.visibility = View.INVISIBLE
            }else if (STORAGE.settings_virtualMouseCheckBoxStatus == "GONE") {
                binding.virtualMouse.visibility = View.GONE
            }else if (STORAGE.settings_virtualMouseCheckBoxStatus == "VISIBLE") {
                binding.virtualMouse.visibility = View.VISIBLE
            }
        }


        binding.homeButton.setOnClickListener() {
            val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sManager.unregisterListener(sListener1)
            sManager.unregisterListener(sListener2)

            getOnOtherActivity1()
        }
        binding.imgSettings.setOnClickListener() {
            getOnOtherActivity2()
        }

        getVirtualMouse()
    }

    override fun onStop() {
        super.onStop()
        isActivityClosed = true
        val sManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sManager.unregisterListener(sListener1)
        sManager.unregisterListener(sListener2)

    }

    fun getVirtualMouse() {

        binding.leftMouseButton.setOnClickListener() {
            btnBlink(R.id.leftMouseButton)
            lastMouseCommand = "leftMouseButton"
        }
        binding.rightMouseButton.setOnClickListener() {
            btnBlink(R.id.rightMouseButton)
            lastMouseCommand = "rightMouseButton"
        }
        binding.holdLeftMouseButton.setOnClickListener() {
            btnBlink(R.id.hold_left_mouse_button)
            lastMouseCommand = "holdLeftMouseButton"
        }
        binding.holdRightMouseButton.setOnClickListener() {
            btnBlink(R.id.hold_right_mouse_button)
            lastMouseCommand = "holdRightMouseButton"
        }
        binding.scrollUp.setOnClickListener() {
            imgBlink(R.id.scroll_up)
            lastMouseCommand = "scrollUp"
        }
        binding.scrollDown.setOnClickListener() {
            imgBlink(R.id.scroll_down)
            lastMouseCommand = "scrollDown"
        }




    }



    private fun onChangeFirebaseListener(dRef: DatabaseReference, setTextValue: (DataSnapshot) -> Unit){
        dRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                setTextValue(snapshot)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    private fun getOnOtherActivity1(){
        val intend = Intent(this@MainActivity, ListOfMainScreens::class.java)
        startActivity(intend)
//        finish()
    }

    private fun getOnOtherActivity2(){
        val intend = Intent(this@MainActivity, Settings::class.java)
        startActivity(intend)
//        finish()
    }




//    fun String.getStringFromIndexToIndex(firstIndex: Int = 0, secondIndex: Int): String {
//        var resultString = ""
//        for (i in firstIndex..secondIndex) {
//            resultString += this.get(i)
//        }
//        return resultString
//
//    }


}
