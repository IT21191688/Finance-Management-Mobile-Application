package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobTipsModal
import kotlinx.android.synthetic.main.activity_job_tips_admin_read.tipId
import kotlinx.android.synthetic.main.activity_job_tips_admin_update.*

class JobTipsAdminUpdate : AppCompatActivity() {
    var jobtip:JobTipsModal = JobTipsModal();

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
        setContentView(R.layout.activity_job_tips_admin_update)
        context = this

        val upBtn=findViewById<Button>(R.id.tipUpdateBtn)
        val caBtn=findViewById<Button>(R.id.tipCancelBtn)

        val value = intent.getStringExtra("id")
        val id= value!!.toInt();
        val db= DbHelperJobs(this);

        System.out.println(id);

        jobtip= db.getJobTip(id);

        System.out.println(jobtip.jobTipsTitle)

        //tipupId.setText(jobtip.tips_id)
        tipTitle.setText(jobtip.jobTipsTitle)
        tipDiscription.setText(jobtip.jobTipsDiscription)

        upBtn.setOnClickListener{

            var id=id
            var title=tipTitle.text.toString()
            var discription=tipDiscription.text.toString()

            val jobtip= JobTipsModal(id,title,discription)
            val success=db.updateJobTip(jobtip);

            if(success==true){
                Toast.makeText(this,"Update Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobTipsAdminRead::class.java))
            }
            else{
                Toast.makeText(this,"Update Unsuccessfull", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobTipsAdminRead::class.java))
            }
        }

        tipCancelBtn.setOnClickListener {
            startActivity(Intent(this,JobTipsAdminRead::class.java))
        }

        imageButton.setOnClickListener{
            startActivity(Intent(this,JobTipsAdminRead::class.java))
        }
    }
}