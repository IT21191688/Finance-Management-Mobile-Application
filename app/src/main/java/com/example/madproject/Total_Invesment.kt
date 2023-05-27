package com.example.madproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.madproject.Database.DBHandler
import com.example.madproject.Database.Invesment

class Total_Invesment : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_total_invesment)

        val db = DBHandler(this).readableDatabase
        val cursor = db.rawQuery("SELECT SUM(${Invesment.Users.COLUMN6}) FROM ${Invesment.Users.TABLE_NAME}", null)
        val cursor1 = db.rawQuery("SELECT SUM(${Invesment.Users.COLUMN7}) FROM ${Invesment.Users.TABLE_NAME}", null)
        if (cursor.moveToFirst()) {
            val sum = cursor.getDouble(0)
            val totalTextView = findViewById<TextView>(R.id.totalInvesmentTI)
            totalTextView.text = sum.toString()
        }
        if (cursor1.moveToFirst()) {
            val sum1 = cursor1.getDouble(0)
            val totalTextView = findViewById<TextView>(R.id.incomeTE)
            totalTextView.text = sum1.toString()
        }
        cursor.close()
        cursor1.close()


        // Use the total value as needed
        // For example, to display it in a TextView with id "totalTextView":
        //val totalTextView = findViewById<TextView>(R.id.totalInvesmentTI)
        //totalTextView

        val button4 : Button = findViewById(R.id.setgoalGoal)

        button4.setOnClickListener {
            startActivity(Intent(this, Set_goal::class.java))
        }

        val im2EI : ImageView = findViewById(R.id.im2EI1)

        im2EI.setOnClickListener {

            var intent = Intent(this, myMain::class.java)
            startActivity(intent)
        }
    }
}