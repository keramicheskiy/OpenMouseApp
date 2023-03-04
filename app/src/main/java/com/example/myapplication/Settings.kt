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

open class Settings : BaseActivity(){

    private lateinit var binding: SettingsBinding
    var maxDeflectAngle: Int = Constants.MAX_DEFLECT_ANGLE_INT
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            intent.putExtra(Constants.MAX_DEFLECT_ANGLE, maxDeflectAngle)
            startActivity(intent)
        }

        binding.saveSettings.setOnClickListener(){
            changeAngleSettings()
        }

    }

    fun changeAngleSettings(){
        val MaxDeflectAngle = binding.enterAngle.text.toString().trim{it <= ' '}

        if (MaxDeflectAngle.isInt()) {
            if (MaxDeflectAngle != "" && MaxDeflectAngle.toIntOrNull() != null) {
//                STORAGE.MaxDeflectAngle = MaxDeflectAngle.toInt()
                maxDeflectAngle = MaxDeflectAngle.toInt()
            }
        } else {
            showErrorSnackBar("Maximum deflection angle can only be an integer.", true)
        }




    }

}