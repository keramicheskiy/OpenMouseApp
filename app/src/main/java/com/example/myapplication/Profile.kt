package com.example.myapplication

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import com.example.myapplication.BaseActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ProfileBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.example.myapplication.R
import com.example.myapplication.SQLiteDB.MyDbManager
import com.example.myapplication.utils.Constants

class Profile : BaseActivity(), View.OnClickListener {
    private lateinit var binding: ProfileBinding
    private var mProgressDialog: Dialog? = null
    private lateinit var mUserDetails: User
    private var mSelectedProfileImageFileUri: Uri? = null
    private var mUserProfileImageUrl: String = ""
    val myDbManager = MyDbManager(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        myDbManager.openDb()
//        var login = myDbManager.readUserDetailsFromDB()[0].login
//        var password = myDbManager.readUserDetailsFromDB()[0].password
//        myDbManager.closeDb()
        var login = intent.getStringExtra(Constants.USER_LOGIN)
        var password = intent.getStringExtra(Constants.USER_PASSWORD)


        binding.profileLoginText.text = login
        binding.profilePasswordText.text = password?.map { it -> '*' }?.putTogether()

        binding.profilePasswordText.setOnClickListener {
            if (binding.profilePasswordText.text != password) {
                binding.profilePasswordText.text = password
            } else {
                binding.profilePasswordText.text = password?.map { it -> '*' }?.putTogether()
            }

        }

        binding.homeButton.setOnClickListener {
            val intent = Intent(this@Profile, ListOfMainScreens::class.java)
            startActivity(intent)
        }

    }




    override fun onClick(v: View?){
        when(v?.id) {
            R.id.ExtraInfoText -> {

            }
        }
    }


}