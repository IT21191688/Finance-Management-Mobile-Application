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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Adapter.JobsAdapterAdmin
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobsModal
import kotlinx.android.synthetic.main.activity_jobs_admin_read.*
import kotlinx.android.synthetic.main.activity_jobs_admin_read.imageButton
import kotlinx.android.synthetic.main.jobadminread.*

class JobsAdminRead : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var btnadd: Button

    var Adapter: JobsAdapterAdmin? = null
    var DbHelp: DbHelperJobs? = null

    var joblist: List<JobsModal> = ArrayList<JobsModal>()
    var linierlayoutManager: LinearLayoutManager? = null

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
        setContentView(R.layout.activity_jobs_admin_read)
        context = this
        recyclerView = findViewById(R.id.recyclerViewJobs)
        DbHelp = DbHelperJobs(this)
        val db = DbHelperJobs(this)

        fetchlist()

        addjobbtn.setOnClickListener {

            startActivity(Intent(this, AddJobsAdmin::class.java))

        }

        updateBtn.setOnClickListener {

            var id = JobId.text.toString()
            println(id+"Hello")
            val intent = Intent(this, JobAdminUpdate::class.java)
            intent.putExtra("id", id)
            startActivity(intent)

        }

        deleteBtn.setOnClickListener {

            var id = jobId.text.toString()

            val Id = id!!.toInt();

            val success = db.delete(Id);

            if (success == true) {
                Toast.makeText(this, "Delete Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, JobsAdminRead::class.java))
            } else {
                Toast.makeText(this, "Delete Unsuccessfull", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, JobsAdminRead::class.java))
            }
        }

        textView3.setOnClickListener{
            startActivity(Intent(this,JobsAdminItems::class.java))
        }

        imageButton.setOnClickListener{
            startActivity(Intent(this,JobsAdminItems::class.java))
        }
    }
    private fun fetchlist() {

        joblist = DbHelp!!.getAllJobs()
        Adapter = JobsAdapterAdmin(joblist, applicationContext);
        linierlayoutManager = LinearLayoutManager(applicationContext);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = Adapter
        Adapter!!.notifyDataSetChanged()

    }
}