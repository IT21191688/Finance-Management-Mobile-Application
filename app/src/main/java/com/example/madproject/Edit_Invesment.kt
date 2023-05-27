package com.example.madproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Database.DBHandler


class Edit_Invesment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_invesment)

        var value = intent.getStringExtra("user")

        val user1 = value!!.toInt()




        val type =  findViewById<EditText>(R.id.etTypeEI)
        val invesmentName = findViewById<EditText>(R.id.etInvesmentNameEI)
        val startDate = findViewById<EditText>(R.id.etStartDateEI)
        val endDate = findViewById<EditText>(R.id.etEndDateEI)
        val currency = findViewById<EditText>(R.id.etCurrencyEI)
        val invesmentValue = findViewById<EditText>(R.id.etInvesmentValueEI)
        val incomeValue = findViewById<EditText>(R.id.etIncomeValueEI)




        val dbHandler = DBHandler(applicationContext)
        var user : List<String> = dbHandler.readAllInfo(user1.toLong())

        if (user.isEmpty()){
            Toast.makeText(this, "No Recode", Toast.LENGTH_LONG).show()
            findViewById<EditText>(R.id.searchVI).text = null
            var intent = Intent(this, View_Individual::class.java)
            startActivity(intent)

        }
        else{
            Toast.makeText(this, "Recode Found", Toast.LENGTH_LONG).show()

            findViewById<EditText>(R.id.etTypeEI).setText(user.get(1).toString())
            findViewById<EditText>(R.id.etInvesmentNameEI).setText(user.get(2).toString())
            findViewById<EditText>(R.id.etStartDateEI).setText(user.get(3).toString())
            findViewById<EditText>(R.id.etEndDateEI).setText(user.get(4).toString())
            findViewById<EditText>(R.id.etCurrencyEI).setText(user.get(5).toString())
            findViewById<EditText>(R.id.etInvesmentValueEI).setText(user.get(6).toString())
            findViewById<EditText>(R.id.etIncomeValueEI).setText(user.get(7).toString())

        }


        val updateEI : Button = findViewById(R.id.updateEI)

        updateEI.setOnClickListener {

            val dbHandler = DBHandler(applicationContext)

            var status : Boolean = dbHandler.updateInfo(
                user1.toLong(),
                findViewById<EditText>(R.id.etTypeEI).text.toString(),
                findViewById<EditText>(R.id.etInvesmentNameEI).text.toString(),
                findViewById<EditText>(R.id.etStartDateEI).text.toString(),
                findViewById<EditText>(R.id.etEndDateEI).text.toString(),
                findViewById<EditText>(R.id.etCurrencyEI).text.toString(),
                findViewById<EditText>(R.id.etInvesmentValueEI).text.toString(),
                findViewById<EditText>(R.id.etIncomeValueEI).text.toString(),

                )
            if (status){
                Toast.makeText(this, "Recode Updated!", Toast.LENGTH_LONG).show()

            }
            else{
                Toast.makeText(this, "Recode Not Updated!", Toast.LENGTH_LONG).show()

            }

        }

        val deleteEI : Button = findViewById(R.id.deleteEI)

        deleteEI.setOnClickListener{
            val dbHandler = DBHandler(applicationContext)
            dbHandler.deleteInfo(user1.toLong())

            Toast.makeText(this, "Recode Deleted!", Toast.LENGTH_LONG).show()

            findViewById<EditText>(R.id.etTypeEI).text = null
            findViewById<EditText>(R.id.etInvesmentNameEI).text = null
            findViewById<EditText>(R.id.etStartDateEI).text = null
            findViewById<EditText>(R.id.etEndDateEI).text = null
            findViewById<EditText>(R.id.etCurrencyEI).text = null
            findViewById<EditText>(R.id.etInvesmentValueEI).text = null
            findViewById<EditText>(R.id.etIncomeValueEI).text = null

            var intent = Intent(this, View_Individual::class.java)
            startActivity(intent)

        }

        val im2EI : ImageView = findViewById(R.id.im2EI)

        im2EI.setOnClickListener {

            var intent = Intent(this, myMain::class.java)
            startActivity(intent)
        }

    }
}
