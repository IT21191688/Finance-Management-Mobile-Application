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
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.madproject.Adapter.ArticlesModal
import com.example.madproject.Adapter.DbHelperArticles

import kotlinx.android.synthetic.main.activity_add_articles_admin.*
import java.io.ByteArrayOutputStream

class AddArticlesAdmin : AppCompatActivity() {
    private lateinit var context: Context
    private val REQUEST_IMAGE_GALLERY=132
    private lateinit var imageFilePath:Uri


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
        setContentView(R.layout.activity_add_articles_admin)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)
        uploadImage.setOnClickListener{
            showAlertBox(this)
        }
        articleSubmit.setOnClickListener{
            var db= DbHelperArticles(this)

            if(!validation()){
                return@setOnClickListener
            }
            var title=articleType.text.toString()
            var date = date.text.toString()
            var description = articleDescription.text.toString()

            //convert imageview to bitmap
            val bitmap = (uploadImage.drawable as BitmapDrawable).bitmap

            //convert bitmap to byte array
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG,90,stream)

            val byteFormat= Base64.encodeToString(stream.toByteArray(),Base64.NO_WRAP)

            val article= ArticlesModal(title,date,description,byteFormat)

            var success=db.addArticles(article)

            if(success===true){
                Toast.makeText(this,"Added Success",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ArtcleReadAdmin::class.java))
            }else{
                Toast.makeText(this,"Added UnSuccess",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ArtcleReadAdmin::class.java))
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==REQUEST_IMAGE_GALLERY && resultCode== Activity.RESULT_OK && data!=null){
            uploadImage.setImageURI(data.data)
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
        builder.setPositiveButton("Gallery"){ dialog,which->dialog.dismiss()
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
        if(articleType.text.isNullOrEmpty()){
            articleType.error="Please Enter the article Name"
            return false;
        }
        if(date.text.isNullOrEmpty()){
            date.error="please Enter the article date"
            return false
        }
        if(articleDescription.text.isNullOrEmpty()){
            articleDescription.error="Please Enter the article Discription"
            return false
        }
        return true
    }
}