package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlinx.android.synthetic.main.activity_job_main.*
import kotlinx.android.synthetic.main.activity_jobs_main_user.*
import kotlinx.android.synthetic.main.activity_jobs_main_user.findJobs
import kotlinx.android.synthetic.main.activity_jobs_main_user.jobTips

class JobMain : AppCompatActivity() {


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
        setContentView(R.layout.activity_job_main)
        context = this

        findJobs.setOnClickListener{
            startActivity(Intent(this,JobsUser::class.java))
        }

        jobTips.setOnClickListener{
            startActivity(Intent(this,JobTipsUser::class.java))
        }
        imageButton.setOnClickListener{
            startActivity(Intent(this,HomePage::class.java))
        }

    }
}