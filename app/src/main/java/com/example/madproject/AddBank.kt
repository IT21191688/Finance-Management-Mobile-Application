package com.example.madproject


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.madproject.Database.DbHelperFinance
import com.example.madproject.Model.BankModel
import kotlinx.android.synthetic.main.activity_add_bank.*

class AddBank : AppCompatActivity() {

    private lateinit var context: Context
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
        setContentView(R.layout.activity_add_bank)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)

        var db= DbHelperFinance(this)
        submit.setOnClickListener{if(!validation()){
            return@setOnClickListener
        }

            var name=name.text.toString()
            var price=price.text.toString()

            var bank= BankModel(name,price)

            var success=db.addBankDetails(bank)

            if(success==true){
                Toast.makeText(this,"Adders Success",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"Not success",Toast.LENGTH_LONG).show()
            }

        }

        imageButton.setOnClickListener{
            startActivity(Intent(this,AccountAdminRead::class.java))
        }
    }
    private fun validation():Boolean{
        if(name.text.isNullOrEmpty()){
            name.error="Please Enter The Bank Name"
            return false;
        }
        if(price.text.isNullOrEmpty()){
            price.error="Please Enter The Amount"
            return false
        }

        return true
    }
    }
