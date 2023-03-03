package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import com.example.myapplication.SQLiteDB.MyDbManager
//import com.example.myapplication.Database.RoomDBManager
import com.example.myapplication.databinding.ActivitySignInBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class ActivitySignIn : BaseActivity() {
    private lateinit var binding: ActivitySignInBinding
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegister.setOnClickListener() {
            val intend = Intent(this@ActivitySignIn, ActivitySignUp::class.java)
            startActivity(intend)
        }

        binding.homeButton.setOnClickListener() {
            val intend = Intent(this@ActivitySignIn, ActivityChooseWayToEnter::class.java)
            startActivity(intend)
        }


        binding.signInButton.setOnClickListener() {
            if (validateRergisterDetails()){
                val auth = Firebase.auth
                auth.signInWithEmailAndPassword(ACCOUNT_DATA.login, ACCOUNT_DATA.password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showErrorSnackBar("Вход выполнен", false)

                        val database = Firebase.database
                        val user = database.getReference("Users/${ACCOUNT_DATA.login.replace(".", "+")}")
                        user.get().addOnSuccessListener {
                            val login = it.child("email").value.toString()
                            val password = it.child("password").value.toString()

                            myDbManager.openDb()
                            myDbManager.deleteAllFromDb()
                            myDbManager.insertUserDetailsToDB(login, password)

                            val intent = Intent(this@ActivitySignIn, ListOfMainScreens::class.java)
                            startActivity(intent)
                        }
                    } else {
                        showErrorSnackBar("Логин или пароль неверен", true)
                        ACCOUNT_DATA.login = ""
                        ACCOUNT_DATA.password = ""
                        binding.enterEmail.text.clear()
                        binding.enterPassword.text.clear()
                    }
                }
            }
        }




    }


    private fun validateRergisterDetails(): Boolean {
        val TextInEnterEmail: String = binding.enterEmail.text.toString()
            .trim { it <= ' ' }
            .decapitalize()

        val TextInEnterPassword: String = binding.enterPassword.text.toString()
            .trim { it <= ' ' }

        return when {
            TextUtils.isEmpty(TextInEnterEmail) -> {
                showErrorSnackBar("Please, enter e-mail.", true)
                false
            }
            TextUtils.isEmpty(TextInEnterPassword) -> {
                showErrorSnackBar("Please, enter password.", true)
                false
            }
            else -> {
                ACCOUNT_DATA.login = TextInEnterEmail
                ACCOUNT_DATA.password = TextInEnterPassword
                true
            }
        }
    }




}
