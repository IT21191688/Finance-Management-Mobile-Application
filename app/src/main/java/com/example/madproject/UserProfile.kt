package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.madproject.Database.DbHelperJobs
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.jobadminread.*
class UserProfile : AppCompatActivity() {

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
        setContentView(R.layout.activity_user_profile)
        context = this

        var name=findViewById<TextView>(R.id.nameU)
        var email=findViewById<TextView>(R.id.emailU)
        var phone=findViewById<TextView>(R.id.phoneU)
        var userName=findViewById<TextView>(R.id.userNameU)
        //var status=findViewById<TextView>(R.id.statusU)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)

        var db=DbHelperJobs(this)

        var user=db.getOneUser(userId)

        var id=user.userId.toString()
        name.setText(user.fName).toString()
        email.setText(user.email).toString()
        phone.setText(user.phoneNo).toString()
        userName.setText(user.userName).toString()
        //status.setText(user.status).toString()

        editProfile.setOnClickListener {

            val intent = Intent(this, EditProfile::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }
        deleteProfile.setOnClickListener {

            val Id = id!!.toInt();
            val success = db.deleteUser(Id);

            if (success == true) {
                Toast.makeText(this, "Delete Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, Sign_Up::class.java))
            } else {
                Toast.makeText(this, "Delete Unsuccessfull", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, UserProfile::class.java))
            }
        }
    }
}