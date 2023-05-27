package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Adapter.DbHelperArticles
import com.example.madproject.Adapter.NewsModal
import com.example.madproject.R
import kotlinx.android.synthetic.main.activity_update_news_admin.*

class UpdateNewsAdmin : AppCompatActivity() {

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
        setContentView(R.layout.activity_update_news_admin)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)

//fetch data
        val value = intent.getStringExtra("id")
        val id =value!!.toInt()
        val db= DbHelperArticles(this)
        var news= NewsModal()
        news=db.getNews(id)

        newsTypeEdit.setText(news.news_Title).toString()
        newsdateEdit.setText(news.news_Date).toString()
        newsDescriptionEdit.setText(news.news_description).toString()
        newsSitelinkEdit.setText(news.news_sitelink).toString()

        //update
        updatenewsbtn.setOnClickListener{

            var newstitle=newsTypeEdit.text.toString()
            var newsdate=newsdateEdit.text.toString()
            var newsdiscription=newsDescriptionEdit.text.toString()
            var newssitelink=newsSitelinkEdit.text.toString()

            news= NewsModal(id,newstitle,newsdate,newsdiscription,newssitelink)
            var success=db.updateNews(news)
            if(success == true){
                Toast.makeText(this,"Update Succesfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,NewsReadAdmin::class.java))
            }else{
                Toast.makeText(this,"Update Unsuccesfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,NewsReadAdmin::class.java))
            }
        }
    }
}