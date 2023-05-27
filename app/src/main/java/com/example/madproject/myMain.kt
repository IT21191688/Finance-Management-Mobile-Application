package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class myMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_main)

        val button1 : Button = findViewById(R.id.button)

        button1.setOnClickListener {
           startActivity(Intent(this,new_invesment::class.java))
        }

        val button2 : Button = findViewById(R.id.button2)

        button2.setOnClickListener {
            startActivity(Intent(this,View_Individual::class.java))
        }

        val button3 : Button = findViewById(R.id.button3)

        button3.setOnClickListener {
            startActivity(Intent(this, Total_Invesment::class.java))
        }
    }

}