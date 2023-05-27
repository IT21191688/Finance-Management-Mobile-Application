package com.example.madproject


import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madproject.Adapter.BankAdapterAdmin
import com.example.madproject.Database.DbHelperFinance
import com.example.madproject.Model.BankModel
import kotlinx.android.synthetic.main.activity_account_admin_read.*


class AccountAdminRead : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var Adapter: BankAdapterAdmin?=null
    var DbHelp: DbHelperFinance?=null

    private lateinit var context: Context
    private val sessionManage: Sessionmanage? = null


    var BankList:List<BankModel> = ArrayList<BankModel>()
    var linierlayoutManager:LinearLayoutManager?=null


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
        setContentView(R.layout.activity_account_admin_read)

        context = this

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("userId", -1)

        recyclerView=findViewById(R.id.recyclerView)

        DbHelp=DbHelperFinance(this)
        val db=DbHelperFinance(this)

        fetchlist()
        var deleteBtn = findViewById<Button>(R.id.deletebtn)

        deleteBtn.setOnClickListener{
            var id = editTextNumber.text.toString()
            println(id)

            val iD = id.toInt()//Casting
            val success = db.deleteBank(iD)

            println(iD)

            if (success == true){
                Toast.makeText(this,"Delete Successfully",Toast.LENGTH_LONG).show()
                startActivity(Intent(this,AccountAdminRead::class.java))
            }else{
                Toast.makeText(this,"Delete Unsuccessfully",Toast.LENGTH_LONG).show()
            }
        }

        accountAdd.setOnClickListener{
            startActivity(Intent(this,AddBank::class.java))
        }

        update.setOnClickListener{
            var id=editTextNumber.text.toString()
            val intent=Intent(this,UpdateBank::class.java)
            intent.putExtra("id",id)
            startActivity(intent)


        }
        imageButton2.setOnClickListener{
            startActivity(Intent(this,HomePage::class.java))
        }


    }

    private fun fetchlist() {

        BankList= DbHelp!!.getAllBanks()
        Adapter = BankAdapterAdmin(BankList, applicationContext);
        linierlayoutManager = LinearLayoutManager(applicationContext);
        recyclerView.layoutManager = LinearLayoutManager(this);
        recyclerView.adapter = Adapter
        Adapter!!.notifyDataSetChanged()

    }
}
