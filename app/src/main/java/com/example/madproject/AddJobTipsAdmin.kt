package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobTipsModal
import kotlinx.android.synthetic.main.activity_account_admin_read.*
import kotlinx.android.synthetic.main.activity_add_job_tips_admin.*
import kotlinx.android.synthetic.main.activity_add_jobs_admin.*
import kotlinx.android.synthetic.main.activity_job_tips_admin_update.*
import kotlinx.android.synthetic.main.jobtipsadminread.*
import kotlinx.android.synthetic.main.jobtipsadminread.tipTitle

class AddJobTipsAdmin : AppCompatActivity() {

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
        setContentView(R.layout.activity_add_job_tips_admin)

        context = this
        val submitBtn=findViewById<Button>(R.id.Add_button)
        val cancelBtn=findViewById<Button>(R.id.cancel_button)
        val tipTit=findViewById<EditText>(R.id.tipsTitle)
        val tipDis=findViewById<EditText>(R.id.tipsDiscription)

        submitBtn.setOnClickListener{

            val db= DbHelperJobs(this);

            if(!validation()){
                return@setOnClickListener
            }

            var tipTitle=tipTit.text.toString()
            var tipDiscription=tipDis.text.toString()

            val jobTip=JobTipsModal(tipTitle,tipDiscription)
            val success=db.addJobTips(jobTip)

            if(success==true){

                Toast.makeText(this,"Success Tip Added",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobTipsAdminRead::class.java));


            }else{
                Toast.makeText(this,"Unsuccess",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,AddJobTipsAdmin::class.java));
            }
        }
        cancelBtn.setOnClickListener{

            startActivity(Intent(this,JobTipsAdminRead::class.java));
        }

    }
    private fun validation():Boolean{
        if(tipsTitle.text.isNullOrEmpty()){
            tipTitle.error="Please Enter the Tip Title"
            return false;
        }
        if(tipsDiscription.text.isNullOrEmpty()){
            tipDiscription.error="please Enter the Tip Discription"
            return false
        }
        return true
    }
}