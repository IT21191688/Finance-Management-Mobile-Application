package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.UserModal
import kotlinx.android.synthetic.main.activity_sign_up.*

class Sign_Up : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var name=findViewById<EditText>(R.id.fName)
        var email=findViewById<EditText>(R.id.email)
        var phoneNum=findViewById<EditText>(R.id.phoneNumber)
        var userName=findViewById<EditText>(R.id.userName)
        var password=findViewById<EditText>(R.id.password)
        var signupBtn=findViewById<Button>(R.id.signUpBtn)
        var loginBtn=findViewById<Button>(R.id.loginBtn)

        signupBtn.setOnClickListener{

            val db=DbHelperJobs(this)
            if(!validation()){
                return@setOnClickListener
            }
            var userFname=name.text.toString()
            var userEmail=email.text.toString()
            var userPhoneNo=phoneNum.text.toString()
            var userUName=userName.text.toString()
            var userPassword=password.text.toString()
            var status="user"

            var user=UserModal(userFname,userEmail,userPhoneNo,userUName,userPassword,status)
            var success=db.addUser(user)

            if(success==true){
                Toast.makeText(this,"USER Registered Success",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,MainActivity::class.java))
            }
            else{
                Toast.makeText(this,"USER Registered Unuccess",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,Sign_Up::class.java))
            }
        }
        loginBtn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    private fun validation():Boolean{
        if(fName.text.isNullOrEmpty()){
            fName.error="Please Enter the First Name"
            return false;
        }
        if(email.text.isNullOrEmpty()){
            email.error="please Enter Email"
            return false
        }
        if(!phoneNumber.text.matches("0\\d{9}".toRegex())){
            phoneNumber.error="Please Enter the Phone Number Correct Format"
            return false
        }
        if(userName.text.isNullOrEmpty()){
            userName.error="Please enter the User Name"
            return false
        }
        if(password.text.isNullOrEmpty()){
            password.error="Please enter the Password"
            return false
        }
        return true
    }
}