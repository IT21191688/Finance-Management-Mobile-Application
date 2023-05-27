package com.example.madproject

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobsModal
import kotlinx.android.synthetic.main.activity_account_admin_read.*
import kotlinx.android.synthetic.main.activity_add_jobs_admin.*
import java.io.ByteArrayOutputStream

class AddJobsAdmin : AppCompatActivity() {
    private val REQUEST_IMAGE_GALLERY=132
    private lateinit var imageFilePath: Uri

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
        setContentView(R.layout.activity_add_jobs_admin)
        context = this

        jobImage.setOnClickListener{

            showAlertBox(this)

        }
        submitBtn.setOnClickListener{

            val db=DbHelperJobs(this);
            if(!validation()){
                return@setOnClickListener
            }

            //val image=uploadImg.get
            val jobname=jobName.text.toString();
            val companyname=companyName.text.toString();
            val jobdiscription=jobDiscription.text.toString();
            val sitelink=siteLink.text.toString()

            //convert imageView to bitmap
            val bitmap= (jobImage.drawable as BitmapDrawable).bitmap
            //convert bitmap to byte array
            val stream= ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,90,stream)
            // convert byte array to string
            val byteFormat= Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP)
            //println(firstname);
            println(byteFormat);

            val job=JobsModal(jobname,companyname,jobdiscription,sitelink,byteFormat)
            val success=db.addJob(job);

            if(success==true){

                Toast.makeText(this,"Data inserted", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobsAdminRead::class.java))

            }else{
                Toast.makeText(this,"Data not inserted", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobsAdminRead::class.java))
            }
        }
        cancelBtn.setOnClickListener{
            startActivity(Intent(this,JobsAdminRead::class.java))
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==REQUEST_IMAGE_GALLERY && resultCode== Activity.RESULT_OK && data!=null){
            jobImage.setImageURI(data.data)
            imageFilePath= data.data!!
        }
        else{
            Toast.makeText(this,"Somthing went wrong", Toast.LENGTH_LONG).show()
        }
    }
    fun showAlertBox(context: Context) {

        val builder = AlertDialog.Builder(context)
        builder.setTitle("Select Image")
        builder.setMessage("Choose your Option")
        builder.setPositiveButton("Gallery"){
                dialog,which->dialog.dismiss()

            val intent= Intent(Intent.ACTION_PICK)
            intent.type="image/*"

            startActivityForResult(intent,REQUEST_IMAGE_GALLERY)
        }
        builder.setNegativeButton("Camera"){
                dialog,which->dialog.dismiss()

        }
        val dialog = builder.create()
        dialog.show()
    }

    private fun validation():Boolean{
        if(jobName.text.isNullOrEmpty()){
            jobName.error="Please Enter the Job Name"
            return false;
        }
        if(companyName.text.isNullOrEmpty()){
            companyName.error="please Enter the Company name"
            return false
        }
        if(jobDiscription.text.isNullOrEmpty()){
            jobDiscription.error="Please Enter the job Discription"
            return false
        }
        if(siteLink.text.isNullOrEmpty()){
            siteLink.error="Please enter the site Link"
            return false
        }
        return true
    }
}