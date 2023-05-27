package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.UserModal
import kotlinx.android.synthetic.main.activity_edit_profile.*

class EditProfile : AppCompatActivity() {

    private lateinit var context: Context
    private val profilepic: ImageView? = null
    private val sessionManage: Sessionmanage? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu items for use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.Home -> {
                startActivity(Intent(this,HomePage::class.java))
                true
            }
            R.id.profile -> {
                startActivity(Intent(this,UserProfile::class.java))
                true
            }
            R.id.contact -> {
                startActivity(Intent(this,Contact::class.java))
                true
            }
            R.id.logout -> {
                val sessionManage = Sessionmanage(context)
                sessionManage.removeSession()
                Toast.makeText(context, "Logout Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(context, MainActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        context = this

        val value = intent.getStringExtra("id")

        val id= value!!.toInt();

        val db= DbHelperJobs(this);

        var user=db.getOneUser(id)

        fNameEdit.setText(user.fName).toString()
        emailEdit.setText(user.email).toString()
        phoneNumberEdit.setText(user.phoneNo).toString()
        userNameEdit.setText(user.userName).toString()
        passwordEdit.setText(user.password).toString()

        profileUpdateBtn.setOnClickListener{

            val db=DbHelperJobs(this)

            var userFname=fNameEdit.text.toString()
            var userEmail=emailEdit.text.toString()
            var userPhoneNo=phoneNumberEdit.text.toString()
            var userUName=userNameEdit.text.toString()
            var userPassword=passwordEdit.text.toString()

            var user= UserModal(id,userFname,userEmail,userPhoneNo,userUName,userPassword)

            var success=db.updateUser(user)

            if(success==true){
                Toast.makeText(this,"USER Updated Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,HomePage::class.java))
            }
            else{
                Toast.makeText(this,"USER Updated Unuccess", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,Sign_Up::class.java))
            }
        }
    }
}