package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.SettingsBinding
import com.example.myapplication.utils.Constants
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.math.atan

open class Settings : AppCompatActivity(){

    private lateinit var binding: SettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val database = Firebase.database
//        val myRef = database.getReference("test/xxx")
//
//        binding.button2.setOnClickListener() {
//            myRef.setValue(binding.edText.text.toString())
//        }
//
//        onChangeListener(myRef)

        binding.showVirtualMouseCheckBox.isChecked = true

        if (STORAGE.settings_virtualMouseCheckBoxStatus in arrayOf("GONE", "INVISIBLE")) {
            binding.showVirtualMouseCheckBox.isChecked = false
        }else if (STORAGE.settings_virtualMouseCheckBoxStatus == "VISIBLE") {
            binding.showVirtualMouseCheckBox.isChecked = true
        }

        binding.showVirtualMouseCheckBox.setOnClickListener(){
            var virtualMouseCheckBoxStatus: String = "INVISIBLE"

            when(binding.showVirtualMouseCheckBox.isChecked) {
                true -> virtualMouseCheckBoxStatus = "VISIBLE"
                else -> virtualMouseCheckBoxStatus = "INVISIBLE"
            }

            STORAGE.settings_virtualMouseCheckBoxStatus = virtualMouseCheckBoxStatus
        }

        binding.homeButton.setOnClickListener(){
            val intent = Intent(this@Settings, MainActivity::class.java)
            startActivity(intent)
        }

        binding.saveSettings.setOnClickListener(){
            changeAngleSettings()
        }

    }

    fun changeAngleSettings(){
        val MaxDeflectAngle = binding.enterAngle.text.toString().trim{it <= ' '}

        if (MaxDeflectAngle != "" && MaxDeflectAngle.toIntOrNull() != null) {
            STORAGE.MaxDeflectAngle = MaxDeflectAngle.toInt()
        }


    }



//    private fun onChangeListener(dRef: DatabaseReference) {
//        dRef.addValueEventListener(object: ValueEventListener{
//            override fun onDataChange(snapshot: DataSnapshot) {
//                binding.apply{
//                    rcView.append("\n")
//                    rcView.append(snapshot.value.toString())
//                }
//            }
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//        })
//
//
//    }







//    fun isInt(num: String): Boolean {
//        var check: Int = 0
//        val numbers: List<String> = listOf(0..9)
//        for (x in 0..num.length) {
//            if (x in numbers) {
//                check++
//            } else {
//
//            }
//
//        }
//        return true
//    }

}