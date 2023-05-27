package com.example.madproject.Database
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {



    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES_INVESTMENT)
        db.execSQL(SQL_CREATE_ENTRIES_GOAL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES_INVESTMENT)
        db.execSQL(SQL_DELETE_ENTRIES_GOAL)
        onCreate(db)
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    companion object {
        // If you change the database schema, you must increment the database version.
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "WealthWaveDB.db"
    }

    private val SQL_CREATE_ENTRIES_INVESTMENT =
        "CREATE TABLE ${Invesment.Users.TABLE_NAME} (" +
                "${Invesment.Users.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Invesment.Users.COLUMN1} TEXT," +
                "${Invesment.Users.COLUMN2} TEXT," +
                "${Invesment.Users.COLUMN3} TEXT," +
                "${Invesment.Users.COLUMN4} TEXT," +
                "${Invesment.Users.COLUMN5} TEXT," +
                "${Invesment.Users.COLUMN6} TEXT," +
                "${Invesment.Users.COLUMN7} TEXT)"

    private val SQL_CREATE_ENTRIES_GOAL =
        "CREATE TABLE ${Invesment.Goal.TABLE_NAME} (" +
                "${Invesment.Goal.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Invesment.Goal.COLUMN1} TEXT," +
                "${Invesment.Goal.COLUMN2} TEXT," +
                "${Invesment.Goal.COLUMN3} TEXT)"


    private val SQL_DELETE_ENTRIES_INVESTMENT = "DROP TABLE IF EXISTS ${Invesment.Users.TABLE_NAME}"
    private val SQL_DELETE_ENTRIES_GOAL = "DROP TABLE IF EXISTS ${Invesment.Goal.TABLE_NAME}"


    fun getGoalCount(): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT COUNT(*) FROM ${Invesment.Goal.TABLE_NAME}", null)
        cursor.moveToFirst()
        val count = cursor.getInt(0)
        cursor.close()
        return count
    }

    fun addInfo(type: String, investmentName: String, startDate: String, endDate: String, currency: String, investmentValue: String, incomeValue: String) {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(Invesment.Users.COLUMN1, type)
            put(Invesment.Users.COLUMN2, investmentName)
            put(Invesment.Users.COLUMN3, startDate)
            put(Invesment.Users.COLUMN4, endDate)
            put(Invesment.Users.COLUMN5, currency)
            put(Invesment.Users.COLUMN6, investmentValue)
            put(Invesment.Users.COLUMN7, incomeValue)
        }

        // Insert the new row, returning the primary key value of the new row
        db.insert(Invesment.Users.TABLE_NAME, null, values)
    }

    fun addGoal(invesmentValue: String, incomeValue: String, Date: String) {
        // Gets the data repository in write mode
        val db = writableDatabase

        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(Invesment.Goal.COLUMN1, invesmentValue)
            put(Invesment.Goal.COLUMN2, incomeValue)
            put(Invesment.Goal.COLUMN3, Date)
        }

        // Insert the new row, returning the primary key value of the new row
        val newRowId = db?.insert(Invesment.Goal.TABLE_NAME, null, values)
    }

    fun updateInfo(id: Long, type: String, invesmentName: String, startDate: String, endDate: String, currency: String, invesmentValue: String, incomeValue: String): Boolean{

        val db = writableDatabase

// New value for one column
        val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(Invesment.Users.COLUMN1, type)
            put(Invesment.Users.COLUMN2, invesmentName)
            put(Invesment.Users.COLUMN3, startDate)
            put(Invesment.Users.COLUMN4, endDate)
            put(Invesment.Users.COLUMN5, currency)
            put(Invesment.Users.COLUMN6, invesmentValue)
            put(Invesment.Users.COLUMN7, incomeValue)
        }

// Which row to update, based on the title
        val selection = "${Invesment.Users.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        val count = db.update(
            Invesment.Users.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        return count > 0
    }

    fun upadeteGoal(id: Long, invesmentValue: String, incomeValue: String, Date: String): Boolean{
        val db = writableDatabase

// New value for one column
        val title = "MyNewTitle"
        val values = ContentValues().apply {
            put(Invesment.Users.COLUMN1, invesmentValue)
            put(Invesment.Users.COLUMN2, incomeValue)
            put(Invesment.Users.COLUMN3, Date)
        }

// Which row to update, based on the title
        val selection = "${Invesment.Goal.COLUMN_ID} LIKE ?"
        val selectionArgs = arrayOf(id.toString())
        val count = db.update(
            Invesment.Goal.TABLE_NAME,
            values,
            selection,
            selectionArgs)

        return count > 0
    }

    fun deleteInfo(id : Long){

        val db = writableDatabase


        // Define 'where' part of query.
        val selection = "${Invesment.Users.COLUMN_ID} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id.toString())
        // Issue SQL statement.
        val deletedRows = db.delete(Invesment.Users.TABLE_NAME, selection, selectionArgs)

    }

    fun deleteGoal(id : Long){

        val db = writableDatabase


        // Define 'where' part of query.
        val selection = "${Invesment.Goal.COLUMN_ID} LIKE ?"
        // Specify arguments in placeholder order.
        val selectionArgs = arrayOf(id.toString())
        // Issue SQL statement.
        val deletedRows = db.delete(Invesment.Goal.TABLE_NAME, selection, selectionArgs)

    }

    fun readAllInfo():List<String>{

        val id = "test";

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            Invesment.Users.COLUMN_ID,
            Invesment.Users.COLUMN1,
            Invesment.Users.COLUMN2,
            Invesment.Users.COLUMN3,
            Invesment.Users.COLUMN4,
            Invesment.Users.COLUMN5,
            Invesment.Users.COLUMN6,
            Invesment.Users.COLUMN7
        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${Invesment.Users.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${Invesment.Users.COLUMN_ID} ASC"

        val cursor = db.query(
            Invesment.Users.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val ids = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val idG = getInt(getColumnIndexOrThrow(Invesment.Users.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN2))

                val con : String = "Entry ID : ${idG.toString()}\nInvesment Name : ${name}"

                ids.add(con)

            }
        }
        cursor.close()
        return ids
    }

    fun readAllInfo(id: Long):ArrayList<String>{

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            Invesment.Users.COLUMN_ID,
            Invesment.Users.COLUMN1,
            Invesment.Users.COLUMN2,
            Invesment.Users.COLUMN3,
            Invesment.Users.COLUMN4,
            Invesment.Users.COLUMN5,
            Invesment.Users.COLUMN6,
            Invesment.Users.COLUMN7
        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${Invesment.Users.COLUMN_ID} = ?" // ("= LIKE ?" - video eke like eka danwa)
        val selectionArgs = arrayOf(id.toString())

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${Invesment.Users.COLUMN_ID} ASC"

        val cursor = db.query(
            Invesment.Users.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,           // don't group the rows
            null,            // don't filter by row groups
            sortOrder              // The sort order
        )

        val userInfo = ArrayList<String>()
        with(cursor) {
            while (moveToNext()) {
                val iD = getInt(getColumnIndexOrThrow(Invesment.Users.COLUMN_ID))
                val type = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN1))
                val invesmentName = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN2))
                val startDate = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN3))
                val endDate = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN4))
                val currency = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN5))
                val invesmentValue = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN6))
                val incomeValue = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN7))

                userInfo.add(iD.toString())
                userInfo.add(type)//0
                userInfo.add(invesmentName)//1
                userInfo.add(startDate)//2
                userInfo.add(endDate)//4
                userInfo.add(currency)//5
                userInfo.add(invesmentValue)//6
                userInfo.add(incomeValue)//7

            }
        }
        cursor.close()
        return userInfo
    }

    fun readAllGoal():List<String>{

        val id = "test";

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            Invesment.Goal.COLUMN_ID,
            Invesment.Goal.COLUMN1,
            Invesment.Goal.COLUMN2,
            Invesment.Goal.COLUMN3
        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${Invesment.Goal.COLUMN_ID} = ?"
        val selectionArgs = arrayOf(id)

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${Invesment.Goal.COLUMN_ID} ASC"

        val cursor = db.query(
            Invesment.Goal.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            null,              // The columns for the WHERE clause
            null,          // The values for the WHERE clause
            null,                   // don't group the rows
            null,                   // don't filter by row groups
            sortOrder               // The sort order
        )

        val ids = mutableListOf<String>()
        with(cursor) {
            while (moveToNext()) {
                val idG = getInt(getColumnIndexOrThrow(Invesment.Users.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN2))

                val con : String = "Entry ID : ${idG.toString()}\nInvesment Name : ${name}"

                ids.add(con)

            }
        }
        cursor.close()
        return ids
    }

    fun readAllGoal(id: Long):ArrayList<String>{

        val db = readableDatabase

// Define a projection that specifies which columns from the database
// you will actually use after this query.
        val projection = arrayOf(

            Invesment.Goal.COLUMN_ID,
            Invesment.Goal.COLUMN1,
            Invesment.Goal.COLUMN2,
            Invesment.Goal.COLUMN3,

        )

// Filter results WHERE "title" = 'My Title'
        val selection = "${Invesment.Goal.COLUMN_ID} = ?" // ("= LIKE ?" - video eke like eka danwa)
        val selectionArgs = arrayOf(id.toString())

// How you want the results sorted in the resulting Cursor
        val sortOrder = "${Invesment.Goal.COLUMN_ID} ASC"

        val cursor = db.query(
            Invesment.Goal.TABLE_NAME,   // The table to query
            projection,             // The array of columns to return (pass null to get all)
            selection,              // The columns for the WHERE clause
            selectionArgs,          // The values for the WHERE clause
            null,           // don't group the rows
            null,            // don't filter by row groups
            sortOrder              // The sort order
        )

        val userInfo = ArrayList<String>()
        with(cursor) {
            while (moveToNext()) {
                val iD = getInt(getColumnIndexOrThrow(Invesment.Users.COLUMN_ID))
                val invesmentValue = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN1))
                val incomeValue = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN2))
                val Date = getString(getColumnIndexOrThrow(Invesment.Users.COLUMN3))


                userInfo.add(iD.toString())
                userInfo.add(incomeValue)//0
                userInfo.add(incomeValue)//1
                userInfo.add(Date)//2


            }
        }
        cursor.close()
        return userInfo
    }

}