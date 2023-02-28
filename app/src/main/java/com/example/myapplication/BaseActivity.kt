package com.example.myapplication

import android.app.Dialog
import android.content.SharedPreferences
import android.view.animation.AnimationUtils
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar

open class BaseActivity : AppCompatActivity() {
    private var mProcessDialog: Dialog? = null

    fun showErrorSnackBar(message: String, errorMessage: Boolean) {
        val snackBar = Snackbar.make(findViewById(android.R.id.content),
            message,
            Snackbar.LENGTH_LONG)

        val snackBarView = snackBar.view

        if (errorMessage) {
            snackBarView.setBackgroundColor(
                ContextCompat.getColor(this@BaseActivity, R.color.colorSnackBarError))
        }else{
            snackBarView.setBackgroundColor(
//                ContextCompat.getColor(this@BaseActivity, R.color.mygreen)
                ContextCompat.getColor(this@BaseActivity, R.color.green)
            )
        }
        snackBar.show()
    }


    fun btnBlink(path: Int) {
        val item = findViewById<Button>(path)
        val animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        item.startAnimation(animAlpha);
    }
    fun textBlink(path: Int) {
        val item = findViewById<TextView>(path)
        val animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        item.startAnimation(animAlpha);
    }
    fun LLBlink(path: Int) {
        val item = findViewById<LinearLayout>(path)
        val animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        item.startAnimation(animAlpha);
    }
    fun imgBlink(path: Int) {
        val item = findViewById<ImageView>(path)
        val animAlpha = AnimationUtils.loadAnimation(this, R.anim.blink);
        item.startAnimation(animAlpha);
    }



    fun showProgressDialog(text: String) {
        mProcessDialog = Dialog(this)
        mProcessDialog!!.setContentView(R.layout.process_dialog)
        mProcessDialog!!.findViewById<TextView>(R.id.process_text).text = text
        mProcessDialog!!.setCancelable(false)
        mProcessDialog!!.setCanceledOnTouchOutside(false)
        mProcessDialog!!.show()

    }
    fun hideProgressDialog(){
        mProcessDialog!!.hide()
    }

//    fun saveText(tag: String, text: String) {
//        val sRef = getPreferences(MODE_PRIVATE)
//        val ed: SharedPreferences.Editor = sRef.edit()
//        ed.putString(tag, text)
//        ed.apply()
//    }
//
//    fun loadText(tag: String): String {
//        val sRef = getPreferences(MODE_PRIVATE)
//        val savedText = sRef.getString(tag, "")!!
//        return savedText
//    }
    fun saveText(tag: String, text: String) {
        val sRef = getPreferences(MODE_PRIVATE)
        val ed: SharedPreferences.Editor = sRef.edit()
        ed.putString(tag, text)
        ed.apply()
    }

    fun loadText(tag: String): String {
        val sRef = getPreferences(MODE_PRIVATE)
        val savedText = sRef.getString(tag, " ")!!
        return savedText
    }

    fun deleteText(tag: String) {
        val sRef = getPreferences(MODE_PRIVATE)
        val editor = sRef.edit()
        editor?.remove(tag)
        editor?.apply()
    }

    fun List<Char>.putTogether(): String{
        var string = ""
        this.forEach {
            string += it.toString()
        }
        return string
    }

    fun String.getStringFromIndexToIndex(firstIndex: Int = 0, secondIndex: Int): String {
        var resultString = ""
        for (i in firstIndex..secondIndex) {
            resultString += this.get(i)
        }
        return resultString

    }

    fun String.getNormalFloat(): Float {
        var result: String = ""
        val coma = ','

        if (this.get(0) == '-' && this.get(1) == coma) {
            result = ("${this.get(0)}0${this.getStringFromIndexToIndex(1, this.length-1)}")
        }
        else if (this.get(0) == coma) {
            result = ("0${this}")
        } else {
            result = this
        }


        return result.replace(coma, '.').toFloat()
    }

    fun String.replaceLast(oldValue: String, newValue: String): String {
        var lastIndexOfElement = this.lastIndexOf(oldValue)
        var a = this.getStringFromIndexToIndex(0, lastIndexOfElement-1)
        var b = this.getStringFromIndexToIndex(lastIndexOfElement, this.length-1).replace(oldValue, newValue)
        return a + b
    }

//    fun String.normalizeStringInt(): String {
//
//
//        return
//    }



}