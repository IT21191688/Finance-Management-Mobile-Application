package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.madproject.Database.DbHelperJobs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var userName=findViewById<EditText>(R.id.userNameL)
        var password=findViewById<EditText>(R.id.passwordL)

        var loginBtn=findViewById<Button>(R.id.LoginBtnL)

        loginBtn.setOnClickListener{

            var db=DbHelperJobs(this)

            if(!validation()){
                return@setOnClickListener
            }

            var name=userName.text.toString()
            var pass=userName.text.toString()

            var success=db.validateUser(name,pass)

            if(success===true){

                var user=db.getUserId(name,pass)

                if(user.status=="user"){

                    val userId = user.userId
                    // Store the user ID in the SharedPreferences
                    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putInt("userId", userId).apply()

                    startActivity(Intent(this,HomePage::class.java))

                }else{

                    val userId = user.userId

                    // Store the user ID in the SharedPreferences
                    val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
                    sharedPreferences.edit().putInt("userId", userId).apply()

                    startActivity(Intent(this,AdminHome::class.java))

                }

            }

        }

        SignUpBtnL.setOnClickListener {
            startActivity(Intent(this,Sign_Up::class.java))
        }

    }
    private fun validation():Boolean{
        if(userNameL.text.isNullOrEmpty()){
            fName.error="Please Enter the First Name"
            return false;
        }
        if(passwordL.text.isNullOrEmpty()){
            email.error="please Enter Email"
            return false
        }

        return true


    }
}