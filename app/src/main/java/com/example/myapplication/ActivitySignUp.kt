package com.example.myapplication

import android.content.Intent
import com.example.myapplication.BaseActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class ActivitySignUp : BaseActivity(){
    private lateinit var binding: ActivitySignUpBinding

    var isThereShouldBeAJoke = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.loggingIn.setOnClickListener() {
            signUp()
        }

        binding.homeButton.setOnClickListener() {
            val intend = Intent(this@ActivitySignUp, ActivityChooseWayToEnter::class.java)
            startActivity(intend)
        }
        binding.SignIn.setOnClickListener() {
            val intend = Intent(this@ActivitySignUp, ActivitySignIn::class.java)
            startActivity(intend)
        }

    }



    private fun signUp() {
        val email : String = binding.EmailId.text.toString().trim{ it <= ' '}.decapitalize()
        val password : String =  binding.password1.text.toString().trim{ it <= ' '}
        if (validateRergisterDetails()) {
            showProgressDialog(getString(R.string.please_wait))
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    hideProgressDialog()
                    val database = Firebase.database
                    val firebaseUser: FirebaseUser = it.result!!.user!!

                    val userId = firebaseUser.uid
                    val user = User(userId,
                        "-",
                        "-",
                        binding.EmailId.text.toString().trim{ it <= ' '},
                        binding.password1.text.toString().trim{ it <= ' '}
                    )
                    FireStoreClass().registerUser(this, user)
                    val myRef = database.getReference(
                        "Users/${binding.EmailId.text.toString().trim{ it <= ' '}.replace(".", "+")}")

                    myRef.setValue(user)

                    val intent = Intent(this@ActivitySignUp, ActivitySignIn::class.java)
                    startActivity(intent)

                } else{
                    hideProgressDialog()
                }
            }
        }
    }




    private fun validateRergisterDetails(): Boolean{

        return when {
            TextUtils.isEmpty(binding.EmailId.text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar("Please, enter EmailId.", true)
                false
            }
            TextUtils.isEmpty(binding.password1.text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar("Please, enter password1.", true)
                false
            }
            TextUtils.isEmpty(binding.password2.text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar("Please, enter password2.", true)
                false
            }
            (!binding.checkBox.isChecked) -> {
                showErrorSnackBar("Please, accept agreement.", true)
                false
            }
            (binding.password1.text.toString().trim {it <= ' '} != binding.password2.text.toString().trim {it <= ' '}) -> {
                showErrorSnackBar("Please, accept agreement.", true)
                false
            }
            else -> {
                if (isThereShouldBeAJoke){
                    isThereShouldBeAJoke = false
                    val userOfTheDay = "starboy69@mail.ru"
                    showErrorSnackBar("Этот пароль уже использует ${userOfTheDay}. Попробуйте другой.",  true)
                    false

                } else {
                    showErrorSnackBar("Валидация выполнена", false)
                    Thread.sleep(100)
                    true
                }

            }
        }



    }
    fun userRegistrationSuccess(user: User) {
        hideProgressDialog()
        Toast.makeText(this@ActivitySignUp, "Регестрация выполнена", Toast.LENGTH_SHORT)
            .show()
        val intend = Intent(this@ActivitySignUp, ActivitySignIn::class.java)
        startActivity(intend)
        finish()
    }





}