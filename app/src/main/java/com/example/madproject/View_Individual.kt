package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.madproject.Database.DBHandler


class View_Individual : AppCompatActivity() {

    lateinit var userList: ListView
    lateinit var dataList: ArrayList<String>
    lateinit var adapter: ArrayAdapter<String>

    lateinit var db: DBHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_individual)

        userList = findViewById(R.id.listviewVI)

        db = DBHandler(applicationContext)

        dataList = db.readAllInfo() as ArrayList<String>

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList)

        userList.setAdapter(adapter)

        val user = findViewById<EditText>(R.id.etUsernameVI)

        val btnSearchUL : Button = findViewById(R.id.searchVI)

        btnSearchUL.setOnClickListener {

            var user = user.text.toString()


            var intent = Intent(this, Edit_Invesment::class.java)
            intent.putExtra("user", user)
            startActivity(intent)

        }

    }

}

