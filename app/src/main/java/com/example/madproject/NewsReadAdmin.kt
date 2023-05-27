package com.example.madproject

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Adapter.DbHelperArticles
import com.example.madproject.Adapter.NewsAdminAdapter
import com.example.madproject.Adapter.NewsModal
import kotlinx.android.synthetic.main.activity_news_read_admin.*
class NewsReadAdmin : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var btnadd: Button
    var Adapter: NewsAdminAdapter?= null
    var DbHelp: DbHelperArticles?=null

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

    var newsList:List<NewsModal> = ArrayList<NewsModal>()
    var linierlayoutManager: LinearLayoutManager?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_read_admin)
        recyclerView = findViewById(R.id.recyclerView)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)


        DbHelp = DbHelperArticles(this)
        val db = DbHelperArticles(this)
        fetchlist()

        var deleteNewsBtn=findViewById<Button>(R.id.deletenewsbtn)
        var addNewsbtn=findViewById<Button>(R.id.addNews)

        addNewsbtn.setOnClickListener{
            startActivity(Intent(this,AddNewsAdmin::class.java))
        }
        deleteNewsBtn.setOnClickListener{
            var id = editNumber.text.toString()
            println(id)
            val iD = id.toInt()//Casting
            val success = db.deleteNews(iD)
            println(iD)
            if (success == true){
                Toast.makeText(this,"Delete Successfully", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,ArtcleReadAdmin::class.java))
            }else{
                Toast.makeText(this,"Delete Unsuccessfully", Toast.LENGTH_LONG).show()
            }
        }
        updatenewsBtn.setOnClickListener{
            var id = editNumber.text.toString()
            println(id)
            val intent = Intent(this, UpdateNewsAdmin::class.java)
            intent.putExtra("id", id)//bind the Id value and send update page
            startActivity(intent)
        }
        imageButton.setOnClickListener{
            startActivity(Intent(this,ArticleAdminMain::class.java))
        }

    }
    private fun fetchlist(){

        newsList=DbHelp!!.getAllNews()
        Adapter= NewsAdminAdapter(newsList,applicationContext);
        linierlayoutManager= LinearLayoutManager(applicationContext);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter=Adapter
        Adapter!!.notifyDataSetChanged()

    }
}