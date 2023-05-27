package com.example.madproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Edit_goal : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_goal)

        val im2EI : ImageView = findViewById(R.id.im2EI1)

        im2EI.setOnClickListener {

            var intent = Intent(this, myMain::class.java)
            startActivity(intent)
        }
    }


}