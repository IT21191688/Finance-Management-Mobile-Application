package com.example.madproject


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Adapter.ArticlesModal
import com.example.madproject.Adapter.DbHelperArticles

import kotlinx.android.synthetic.main.activity_add_articles_admin.*
import kotlinx.android.synthetic.main.activity_update_articles_admin.*
import kotlinx.android.synthetic.main.articleread.*
class UpdateArticlesAdmin : AppCompatActivity() {
    var article: ArticlesModal = ArticlesModal();

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
        setContentView(R.layout.activity_update_articles_admin)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)

        //fetch data
        val value = intent.getStringExtra("id")
        val id =value!!.toInt()
        val db= DbHelperArticles(this)
        article=db.getArticles(id)
        articleTypeEdit.setText(article.article_Title)
        dateEdit.setText(article.article_Date)
        articleDescriptionEdit.setText(article.article_description)

        //update
        articleUpdate.setOnClickListener{
            var title=articleTypeEdit.text.toString()
            var date=dateEdit.text.toString()
            var discription=articleDescriptionEdit.text.toString()
            article=ArticlesModal(id,title,date,discription)
            var success=db.updateArticles(article)
            if(success == true){
                Toast.makeText(this,"Update Succesfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ArtcleReadAdmin::class.java))
            }else{
                Toast.makeText(this,"Update Unsuccesfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ArtcleReadAdmin::class.java))
            }
        }
    }
}