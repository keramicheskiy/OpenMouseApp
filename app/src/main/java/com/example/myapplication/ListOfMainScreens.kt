package com.example.myapplication

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.myapplication.Database.DataStoreManager
import com.example.myapplication.Database.DbFunctions
import com.example.myapplication.SQLiteDB.MyDbManager
//import com.example.myapplication.Database.RoomDBManager
import com.example.myapplication.databinding.ListOfMainScreensBinding
import com.example.myapplication.utils.Constants
import com.example.myapplication.utils.UsersPrivateDetailsDataClass
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import java.util.concurrent.CountDownLatch

class ListOfMainScreens : BaseActivity(){ // , View.OnClickListener
    private lateinit var binding: ListOfMainScreensBinding
    lateinit var mUserDetails: User
    lateinit var value: String
    lateinit var login: String
    lateinit var password: String

    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ListOfMainScreensBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        var isBtnTranslateWasPressed = true



        // @CommonActions

        val done = CountDownLatch(1)
        val builder = StringBuilder()
        GlobalScope.launch {
            val a = DataStoreManager.getStringValue(this@ListOfMainScreens, Constants.PRIVATE_USER_DETAILS, Constants.DEFAULT_VALUE)
            builder.append(a)
            done.countDown()
        }
        done.await()
        var privateUserDetailsString = builder.toString()
        if (privateUserDetailsString != Constants.DEFAULT_VALUE) {
            var obj: UsersPrivateDetailsDataClass = Json.decodeFromString<UsersPrivateDetailsDataClass>(privateUserDetailsString)
            login = obj.login
            password = obj.password
        } else {
            val intent = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
            startActivity(intent)
        }

//        val json = Json.encodeToString(UsersPrivateDetailsDataClass("ya@s.ru", "pass123"))



//        myDbManager.openDb()
//
//        if (myDbManager.readUserDetailsFromDB().toString().length != 2) {
//            login = myDbManager.readUserDetailsFromDB()[0].login
//            password = myDbManager.readUserDetailsFromDB()[0].password
////            validateUserOrLogOut(login, password)
//
////            binding.profileLoginText.text = login
////            binding.profilePasswordText.text = password
//        } else {
//            val intent = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
//            startActivity(intent)
//        }







        // @BottomBarCommands

        getBottomBar()



        // @Main_page_activity

//        binding.homeButton.setOnClickListener() {
//            val intend = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
//            startActivity(intend)
//        }

        binding.redirectToFirstFunc.setOnClickListener() {
            val intent = Intent(this@ListOfMainScreens, MainActivity::class.java)
            startActivity(intent)
        }

        binding.ExtraInfoText.setOnClickListener {

            if (!isBtnTranslateWasPressed) {
                binding.ExtraInfoText.setText(R.string.engFirstFuncText)
                isBtnTranslateWasPressed = true
            } else if (isBtnTranslateWasPressed) {
                binding.ExtraInfoText.setText(R.string.ruFirstFuncText)
                isBtnTranslateWasPressed = false
            }


        }



        // @Functions_activity

        binding.btnOpenFunc.setOnClickListener {
            textBlink(R.id.btnOpenFunc)

            binding.func1.visibility = View.VISIBLE
            binding.func2.visibility = View.GONE
            binding.func3.visibility = View.GONE
        }

        binding.btnDevelopingFunc.setOnClickListener {
            textBlink(R.id.btnDevelopingFunc)

            binding.func1.visibility = View.GONE
            binding.func2.visibility = View.VISIBLE
            binding.func3.visibility = View.VISIBLE
        }

        binding.func1.setOnClickListener(){
            LLBlink(R.id.func1)
            Thread.sleep(150)
            val intent = Intent(this@ListOfMainScreens, MainActivity::class.java)
            startActivity(intent)
        }
        binding.func2.setOnClickListener(){
            LLBlink(R.id.func2)
            showErrorSnackBar("This function is in progress of developing...", true)
        }
        binding.func3.setOnClickListener(){
            LLBlink(R.id.func3)
            showErrorSnackBar("This function is in progress of developing...", true)
        }

        //  @Animations

//        animate()

        // @Profile_activity

        binding.redirectToAccountDetails.setOnClickListener {
            val intent = Intent(this@ListOfMainScreens, Profile::class.java)
            intent.putExtra(Constants.USER_LOGIN, login)
            intent.putExtra(Constants.USER_PASSWORD, password)
            startActivity(intent)
        }

        binding.logOutFromAccount.setOnClickListener {
            Firebase.auth.signOut()
            login = ""
            password = ""
//            myDbManager.deleteAllFromDb()
            GlobalScope.launch {
                DataStoreManager.saveValue(this@ListOfMainScreens, Constants.PRIVATE_USER_DETAILS, Constants.DEFAULT_VALUE)
            }
            val intend = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
            startActivity(intend)
        }
    }

//    override fun onClick(v: View?) {
//        when (v?.id) {
//            R.id.accountText -> {
//            }
//        }
//    }

    override fun onPause() {
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
//        myDbManager.closeDb()
    }




    fun directMain() {
        binding.profileActivity.visibility = View.GONE
        binding.functionsActivity.visibility = View.GONE
        binding.mainPageActivity.visibility = View.VISIBLE

        binding.directMain.setBackgroundResource(R.drawable.cool_bottom_lines_elements)
        binding.directFunctions.background = null
        binding.directProfile.background = null
    }

    fun directFunctions() {
        binding.profileActivity.visibility = View.GONE
        binding.mainPageActivity.visibility = View.GONE
        binding.functionsActivity.visibility = View.VISIBLE

//            binding.directFunctions.background = R.color.darkThemeBackround.toDrawable()
        binding.directFunctions.setBackgroundResource(R.drawable.cool_bottom_lines_elements)
        binding.directMain.background = null
        binding.directProfile.background = null
    }

    fun directProfile() {
        binding.mainPageActivity.visibility = View.GONE
        binding.functionsActivity.visibility = View.GONE
        binding.profileActivity.visibility = View.VISIBLE

        binding.directProfile.setBackgroundResource(R.drawable.cool_bottom_lines_elements)
        binding.directFunctions.background = null
        binding.directMain.background = null
    }

    fun validateUserOrLogOut(login: String, password: String){
        val auth = Firebase.auth
        val intent = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
        if (!login.isBlank() && !password.isBlank()) {
            auth.signInWithEmailAndPassword(login, password).addOnFailureListener {
                startActivity(intent)
            }
        } else {
            myDbManager.deleteAllFromDb()
            startActivity(intent)
        }

    }

    fun validateUserOrLogOut() {
        val auth = Firebase.auth
        GlobalScope.launch {
            val userDetails = DataStoreManager.getStringValue(
                this@ListOfMainScreens,
                Constants.USER_DETAILS,
                ""
            )

            val User = userDetails

            if (!userDetails.isBlank()) {
                auth.signInWithEmailAndPassword(
                    loadText(Constants.USER_LOGIN),
                    loadText(Constants.USER_PASSWORD)
                )
                    .addOnFailureListener {
                        val intent =
                            Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
                        startActivity(intent)
                    }
//                binding.ab.text = loadText(Constants.USER_LOGIN)
//                binding.ba.text = loadText(Constants.USER_PASSWORD)
            } else {
                val intent = Intent(this@ListOfMainScreens, ActivityChooseWayToEnter::class.java)
                startActivity(intent)
            }
        }



    }

    fun getBottomBar() {
        binding.directMain.setOnClickListener() {
            directMain()
        }
        binding.directFunctions.setOnClickListener() {
            directFunctions()
        }
        binding.directProfile.setOnClickListener() {
            directProfile()
        }
    }


}