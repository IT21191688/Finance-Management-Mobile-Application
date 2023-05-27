package com.example.madproject

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.madproject.Database.DBHandler
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


class new_invesment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_invesment)


// ...

        val currencyPattern: Pattern = Pattern.compile("^\\$?([1-9]\\d{0,2}(,\\d{3})*|([1-9]\\d*))\\.\\d{2}$")

        val etinvesmentValueNE = findViewById<EditText>(R.id.etinvesmentValueNE)

        etinvesmentValueNE.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!currencyPattern.matcher(s.toString()).matches()) {
                    etinvesmentValueNE.error = "Please enter a valid currency value"
                } else {
                    etinvesmentValueNE.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }
        })

        val currencyPattern1: Pattern = Pattern.compile("^\\$?([1-9]\\d{0,2}(,\\d{3})*|([1-9]\\d*))\\.\\d{2}$")

        val etincomeValueNE = findViewById<EditText>(R.id.etincomeValueNE)

        etincomeValueNE.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!currencyPattern1.matcher(s.toString()).matches()) {
                    etincomeValueNE.error = "Please enter a valid currency value"
                } else {
                    etincomeValueNE.error = null
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Not used
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Not used
            }
        })

        val etStartDateNE = findViewById<EditText>(R.id.etStartDateNE)
        etStartDateNE.isFocusable = false

        val calendar = Calendar.getInstance()
        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR, year)
            calendar.set(Calendar.MONTH, month)
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            etStartDateNE.setText(dateFormat.format(calendar.time))
        }

        etStartDateNE.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this@new_invesment,
                dateSetListener,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        val etEndDateNE = findViewById<EditText>(R.id.etEndDateNE)
        etEndDateNE.isFocusable = false

        val calendar1 = Calendar.getInstance()
        val dateSetListener1 = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            calendar1.set(Calendar.YEAR, year)
            calendar1.set(Calendar.MONTH, month)
            calendar1.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            etEndDateNE.setText(dateFormat.format(calendar1.time))
        }

        etEndDateNE.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this@new_invesment,
                dateSetListener1,
                calendar1.get(Calendar.YEAR),
                calendar1.get(Calendar.MONTH),
                calendar1.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }






        val viewNE : Button = findViewById(R.id.viewNE)

        viewNE.setOnClickListener {
            startActivity(Intent(this,View_Individual::class.java))
        }

        val submitNE : Button = findViewById(R.id.submitNE)

        submitNE.setOnClickListener {

            if (!validateInputFields()) {
                return@setOnClickListener
            }


            val dbHandler = DBHandler(applicationContext)

            val success = dbHandler.addInfo(
                findViewById<EditText>(R.id.etTypeNE).text.toString(),
                findViewById<EditText>(R.id.etInvesmentNameNE).text.toString(),
                findViewById<EditText>(R.id.etStartDateNE).text.toString(),
                findViewById<EditText>(R.id.etEndDateNE).text.toString(),
                findViewById<EditText>(R.id.etCurrencyNE).text.toString(),
                findViewById<EditText>(R.id.etinvesmentValueNE).text.toString(),
                findViewById<EditText>(R.id.etincomeValueNE).text.toString(),


                )
            Toast.makeText(this, "Data Added Successfully!", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, new_invesment::class.java))

            findViewById<EditText>(R.id.etTypeNE).text = null
            findViewById<EditText>(R.id.etInvesmentNameNE).text = null
            findViewById<EditText>(R.id.etStartDateNE).text = null
            findViewById<EditText>(R.id.etEndDateNE).text = null
            findViewById<EditText>(R.id.etCurrencyNE).text = null
            findViewById<EditText>(R.id.etinvesmentValueNE).text = null
            findViewById<EditText>(R.id.etincomeValueNE).text = null
        }

        val clearNE : Button = findViewById(R.id.clearGoal)

        clearNE.setOnClickListener {
            startActivity(Intent(this,new_invesment::class.java))

            findViewById<EditText>(R.id.etTypeNE).text = null
            findViewById<EditText>(R.id.etInvesmentNameNE).text = null
            findViewById<EditText>(R.id.etStartDateNE).text = null
            findViewById<EditText>(R.id.etEndDateNE).text = null
            findViewById<EditText>(R.id.etCurrencyNE).text = null
            findViewById<EditText>(R.id.etinvesmentValueNE).text = null
            findViewById<EditText>(R.id.etincomeValueNE).text = null

        }

        val im2EI : ImageView = findViewById(R.id.im2EI)

        im2EI.setOnClickListener {

            var intent = Intent(this, myMain::class.java)
            startActivity(intent)
        }

    }



    private fun validateInputFields(): Boolean {
        val type =  findViewById<EditText>(R.id.etTypeNE)
        val invesmentName = findViewById<EditText>(R.id.etInvesmentNameNE)
        val startDate = findViewById<EditText>(R.id.etStartDateNE)
        val endDate = findViewById<EditText>(R.id.etEndDateNE)
        val currency = findViewById<EditText>(R.id.etCurrencyNE)
        val invesmentValue = findViewById<EditText>(R.id.etinvesmentValueNE)
        val incomeValue = findViewById<EditText>(R.id.etincomeValueNE)


        if (type.text.isNullOrEmpty()) {
            type.error = "Please enter a type"
            return false
        }

        if (invesmentName.text.isNullOrEmpty()) {
            invesmentName.error = "Please enter a invesment Name"
            return false
        }

        if (startDate.text.isNullOrEmpty()) {
            startDate.error = "Please enter a start Date"
            return false
        }

        if (endDate.text.isNullOrEmpty()) {
            endDate.error = "Please enter a end Date"
            return false
        }

        if (currency.text.isNullOrEmpty()) {
            currency.error = "Please enter a currency"
            return false
        }

        if (invesmentValue.text.isNullOrEmpty()) {
            invesmentValue.error = "Please enter a invesment Value"
            return false
        }

        if (incomeValue.text.isNullOrEmpty()) {
            incomeValue.error = "Please enter a income Value"
            return false
        }
        return true
    }



}


