package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Adapter.JobsAdapterUser
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobsModal
import kotlinx.android.synthetic.main.activity_jobs_user.*
import java.lang.Character.toLowerCase
import java.util.*
import kotlin.collections.ArrayList

class JobsUser : AppCompatActivity() {


    lateinit var recyclerView: RecyclerView

    var Adapter:JobsAdapterUser ?= null
    var DbHelp:DbHelperJobs ?=null

    var joblist:List<JobsModal> = ArrayList<JobsModal>()
    var linierlayoutManager: LinearLayoutManager?= null



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
        setContentView(R.layout.activity_jobs_user)
        context = this
        recyclerView=findViewById(R.id.recyclerViewUserJobs)
        DbHelp= DbHelperJobs(this)
        val db= DbHelperJobs(this)


        fetchlist()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                // Call searchList function whenever the search text changes
                if (p0 != null) {
                    searchList(p0)
                }
                return true
            }
        })






        imageButton.setOnClickListener{
            startActivity(Intent(this,JobMain::class.java))
        }

    }

    fun searchList(text: String) {
        val searchList = ArrayList<JobsModal>()

        for (job in joblist) {
            if (job.companyName.toLowerCase(Locale.getDefault()).contains(text.toLowerCase(Locale.getDefault()))) {
                searchList.add(job)
            }
        }

        // Create a new JobsAdapterUser with the search results and update the RecyclerView
        Adapter = JobsAdapterUser(searchList, applicationContext)
        recyclerView.adapter = Adapter
        Adapter!!.notifyDataSetChanged()
    }

    private fun fetchlist(){

        joblist=DbHelp!!.getAllJobs()
        Adapter= JobsAdapterUser(joblist,applicationContext);
        linierlayoutManager= LinearLayoutManager(applicationContext);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter=Adapter
        Adapter!!.notifyDataSetChanged()

    }
}