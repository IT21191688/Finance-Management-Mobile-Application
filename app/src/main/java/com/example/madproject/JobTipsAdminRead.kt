package com.example.madproject

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Adapter.JobTipsAdapterAdmin
import com.example.madproject.Database.DbHelperJobs
import com.example.madproject.Model.JobTipsModal
import kotlinx.android.synthetic.main.activity_job_tips_admin_read.*
import kotlinx.android.synthetic.main.activity_jobs_admin_read.*
import kotlinx.android.synthetic.main.activity_jobs_admin_read.imageButton
import kotlinx.android.synthetic.main.jobadminread.*

class JobTipsAdminRead : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    var Adapter: JobTipsAdapterAdmin?= null
    var DbHelp: DbHelperJobs?=null

    var tipslist:List<JobTipsModal> = ArrayList<JobTipsModal>()
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
        setContentView(R.layout.activity_job_tips_admin_read)

        recyclerView=findViewById(R.id.recyclerViewTips)
        DbHelp= DbHelperJobs(this)
        val db= DbHelperJobs(this)

        val updateBtn=findViewById<Button>(R.id.updateTipBtn)
        val deleteBtn=findViewById<Button>(R.id.deleteTipBtn)
        val tipid=findViewById<EditText>(R.id.tipId)

        fetchlist()

        //val addNewTip=findViewById<Button>(R.id.Add_new_tip)

        Add_new_tip.setOnClickListener{
            startActivity(Intent(this,AddJobTipsAdmin::class.java))
        }

        updateBtn.setOnClickListener{

            var id=tipid.text.toString()
            val intent = Intent(this, JobTipsAdminUpdate::class.java)
            intent.putExtra("id", id)
            startActivity(intent)


        }

        deleteBtn.setOnClickListener{

            var id=tipid.text.toString()

            val Id= id!!.toInt();

            val success=db.deleteJob(Id);

            if(success==true){
                Toast.makeText(this,"Delete Success", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobTipsAdminRead::class.java))
            }
            else{
                Toast.makeText(this,"Delete Unsuccessfull", Toast.LENGTH_LONG).show()
                startActivity(Intent(this,JobTipsAdminRead::class.java))
            }
        }

        imageButton.setOnClickListener{
            startActivity(Intent(this,JobsAdminItems::class.java))
        }


    }
    private fun fetchlist(){

        tipslist=DbHelp!!.getAllJobTips()
        Adapter= JobTipsAdapterAdmin(tipslist,applicationContext);
        linierlayoutManager= LinearLayoutManager(applicationContext);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter=Adapter
        Adapter!!.notifyDataSetChanged()

    }
}