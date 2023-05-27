package com.example.madproject.Database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.madproject.Model.BankModel


class DbHelperFinance(context: Context): SQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    companion object{

        private val DB_NAME="CustomerManagement"
        private val DB_VERSION=1

        private val TABLE_NAME10="budget"
        private val BANK_ID="id"
        private val BANK_NAME="bankName"
        private val BANK_PRICE="bankPrice"

    }

    override fun onCreate(p0: SQLiteDatabase?) {

        val CREATE_TABLE10="CREATE TABLE $TABLE_NAME10 ($BANK_ID INTEGER PRIMARY KEY,$BANK_NAME TEXT,$BANK_PRICE TEXT)"
        p0?.execSQL(CREATE_TABLE10);

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val DROP_TABLE10="DROP TABLE IF EXISTs $TABLE_NAME10"
        p0?.execSQL(DROP_TABLE10)
        onCreate(p0)
    }
// Functions

    fun getAllBanks():List<BankModel>{
        val bankList=ArrayList<BankModel>()
        val db=writableDatabase
        val selectQuery="SELECT * FROM $TABLE_NAME10"
        val cursor=db.rawQuery(selectQuery,null)

        if(cursor != null){
            if(cursor.moveToFirst()){
                do{

                    val banks=BankModel()

                    banks.id=cursor.getInt(0)
                    banks.bankName=cursor.getString(1)
                    banks.bankPrice=cursor.getString(2)

                    bankList.add(banks)

                }while(cursor.moveToNext())
            }
        }

        cursor.close()
        return bankList


    }
    fun addBankDetails(tip:BankModel):Boolean{

        val db=this.writableDatabase
        val values= ContentValues();
        values.put(BANK_NAME,tip.bankName)
        values.put(BANK_PRICE,tip.bankPrice)

        val success=db.insert(TABLE_NAME10,null,values)
        db.close()

        return (Integer.parseInt("$success")!=-1)
    }


    fun getBankDetails(_id:Int):BankModel{

        val Bank=BankModel()
        val db=writableDatabase
        val selectQueary="SELECT * FROM $TABLE_NAME10 WHERE $BANK_ID=$_id"
        val cursor: Cursor =db.rawQuery(selectQueary,null)


        cursor?.moveToFirst()

        Bank.id=cursor.getInt(0)
        Bank.bankName=cursor.getString(1)
        Bank.bankPrice=cursor.getString(2)


        cursor.close()

        return Bank;

    }

    fun deleteBank(_id:Int):Boolean{

        val db=this.writableDatabase

        val success=db.delete(TABLE_NAME10, BANK_ID+"=?", arrayOf(_id.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1;

    }

    fun updateBank(jobTip: BankModel):Boolean{

        val db=this.writableDatabase
        val values= ContentValues()
        values.put(BANK_NAME,jobTip.bankName)
        values.put(BANK_PRICE,jobTip.bankPrice)

        val success=db.update(TABLE_NAME10,values, BANK_ID+"=?",arrayOf(jobTip.id.toString())).toLong()

        db.close()
        return Integer.parseInt("$success")!=-1

    }
}
