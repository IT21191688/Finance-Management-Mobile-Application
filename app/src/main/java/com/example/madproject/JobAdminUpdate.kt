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
import com.example.madproject.Model.JobsModal
import kotlinx.android.synthetic.main.activity_job_admin_update.*

class JobAdminUpdate : AppCompatActivity() {

    var job:JobsModal = JobsModal();

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
        setContentView(R.layout.activity_job_admin_update)
        context = this

        val value = intent.getStringExtra("id")
        val id= value!!.toInt();
        val db= DbHelperJobs(this);
        System.out.println(id);

        job= db.getJob(id);
        jobName.setText(job.jobName)
        companyName.setText(job.companyName)
        jobDiscription.setText(job.jobDiscription)
        siteLink.setText(job.siteLink)

        upSubmitBtn.setOnClickListener{

            var Id=id
            var job_name=jobName.text.toString()
            var company_name=companyName.text.toString()
            var job_discription=jobDiscription.text.toString()
            var site_link=siteLink.text.toString()

            val job=JobsModal(Id,job_name,company_name,job_discription,site_link)

            val success=db.updateJob(job);

            if(success==true){
                Toast.makeText(this,"Update Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobsAdminRead::class.java))
            }
            else{
                Toast.makeText(this,"Update Unsuccessfull", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobsAdminRead::class.java))
            }


        }

        imageButton.setOnClickListener{
            startActivity(Intent(this,JobsAdminRead::class.java))
        }

        upCancelBtn.setOnClickListener {

            startActivity(Intent(this,JobsAdminRead::class.java))
        }


    }
}